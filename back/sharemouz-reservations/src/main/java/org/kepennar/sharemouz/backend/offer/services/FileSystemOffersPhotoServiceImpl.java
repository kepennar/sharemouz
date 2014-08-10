package org.kepennar.sharemouz.backend.offer.services;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoReadException;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoWriteException;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;

import static org.kepennar.sharemouz.backend.SpringProfiles.SPRING_PROFILE_DEVELOPMENT;
import static org.kepennar.sharemouz.backend.SpringProfiles.SPRING_PROFILE_TEST;
/**
 * Created by kepennar on 03/08/14.
 */
@Profile({SPRING_PROFILE_DEVELOPMENT, SPRING_PROFILE_TEST})
@Service
public class FileSystemOffersPhotoServiceImpl implements OffersPhotoService {
    public static File STORAGE_DIRECTORY = new File(SystemUtils.getUserHome(), "sharemouz");

    private final OffersService offersService;

    @Inject
    public FileSystemOffersPhotoServiceImpl(OffersService offersService) {
        this.offersService = offersService;
    }

    @Override
    public OfferPhoto readOfferPhoto(String offerId) {
        InputStream fileInputSteam = null;
        try {
            Offer offer = offersService.findById(offerId).get();
            fileInputSteam = new FileInputStream(fileForPhoto(offerId));
            byte[] data = IOUtils.toByteArray(fileInputSteam);
            return new OfferPhoto(offerId, data, MediaType.parseMediaType(offer.getOfferPhotoMediaType()));
        } catch (Exception e) {
            throw new OfferPhotoReadException(offerId, e);
        } finally {
            IOUtils.closeQuietly(fileInputSteam);
        }
    }

    @Override
    public void writeOfferPhoto(String userId, MediaType ext, byte[] bytesForPhoto) {

        Offer offer = offersService.findById(userId).get();
        Offer updatedOffer = new Offer();
        updatedOffer.setOfferPhotoMediaType(ext.toString());
        updatedOffer.setOfferPhotoImported(true);
        offersService.update(offer, updatedOffer);

        ByteArrayInputStream byteArrayInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileForPhoto(userId));
            byteArrayInputStream = new ByteArrayInputStream(bytesForPhoto);
            IOUtils.copy(byteArrayInputStream, fileOutputStream);
        } catch (IOException e) {
            throw new OfferPhotoWriteException(userId, e);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(byteArrayInputStream);
        }
    }

    private File fileForPhoto(String offerId) {
        return new File(STORAGE_DIRECTORY, offerId);
    }
}
