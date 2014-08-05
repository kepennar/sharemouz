package org.kepennar.sharemouz.backend.offer.services;

import org.apache.commons.io.IOUtils;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoReadException;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoWriteException;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;

import static org.kepennar.sharemouz.backend.ReservationApplication.STORAGE_DIRECTORY;
import static org.kepennar.sharemouz.backend.config.Constants.SPRING_PROFILE_DEVELOPMENT;
import static org.kepennar.sharemouz.backend.config.Constants.SPRING_PROFILE_TEST;

/**
 * Created by kepennar on 03/08/14.
 */
@Profile({SPRING_PROFILE_DEVELOPMENT, SPRING_PROFILE_TEST})
@Service
public class FileSystemOffersPhotoServiceImpl implements OffersPhotoService {

    private final OffersService offersService;

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
        offer.setOfferPhotoMediaType(ext.toString());
        offer.setOfferPhotoImported(true);
        offersService.update(offer);

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
