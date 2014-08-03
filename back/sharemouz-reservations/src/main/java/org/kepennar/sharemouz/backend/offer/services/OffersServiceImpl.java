package org.kepennar.sharemouz.backend.offer.services;

import org.apache.commons.io.IOUtils;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoReadException;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.kepennar.sharemouz.backend.offer.repository.OfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.kepennar.sharemouz.backend.ReservationApplication.STORAGE_DIRECTORY;

/**
 * Created by kepennar on 03/08/14.
 */
@Service
public class OffersServiceImpl implements OffersService {

    private final OfferRepository repo;

    @Inject
    public OffersServiceImpl(OfferRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(repo.findOne(id));
    }

    @Override
    public Page<Offer> find(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void deleteById(String id) {
        repo.delete(id);
    }

    @Override
    public Offer create(Offer offer) {
        return repo.save(offer);
    }

    @Override
    public Optional<Offer> update(Offer offer) {
        Offer dbOffer = repo.findOne(offer.getId());
        if (dbOffer == null) {
            return Optional.empty();
        }
        dbOffer.updateProperties(offer);
        return Optional.of(repo.save(dbOffer));
    }

    @Override
    public OfferPhoto readOfferPhoto(String offerId) {
        InputStream fileInputSteam = null;
        try {
            Offer offer = findById(offerId).get();
            fileInputSteam = new FileInputStream(fileForPhoto(offerId));
            byte[] data = IOUtils.toByteArray(fileInputSteam);
            return new OfferPhoto(offerId, data, MediaType.parseMediaType(offer.getOfferPhotoMediaType()));
        } catch (Exception e) {
            throw new OfferPhotoReadException(offerId, e);
        } finally {
            IOUtils.closeQuietly(fileInputSteam);
        }
    }

    private File fileForPhoto(String offerId) {
        return new File(STORAGE_DIRECTORY, offerId);
    }


}
