package org.kepennar.sharemouz.backend.offer.services;

import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.springframework.http.MediaType;

/**
 * Created by kepennar on 03/08/14.
 */
public interface OffersPhotoService {

    OfferPhoto readOfferPhoto(String offerId);
    void writeOfferPhoto(String userId, MediaType ext, byte[] bytesForPhoto);
}
