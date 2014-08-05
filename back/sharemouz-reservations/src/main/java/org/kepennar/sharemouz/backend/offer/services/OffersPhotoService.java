package org.kepennar.sharemouz.backend.offer.services;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import java.util.Optional;

/**
 * Created by kepennar on 03/08/14.
 */
public interface OffersPhotoService {

    OfferPhoto readOfferPhoto(String offerId);
    void writeOfferPhoto(String userId, MediaType ext, byte[] bytesForPhoto);
}
