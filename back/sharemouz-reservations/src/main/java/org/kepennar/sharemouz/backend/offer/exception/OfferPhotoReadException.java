package org.kepennar.sharemouz.backend.offer.exception;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class OfferPhotoReadException extends OfferException {

    private static final long serialVersionUID = 1L;

    public OfferPhotoReadException(Offer offer, Throwable cause) {
        super(offer, cause);
    }

    public OfferPhotoReadException(String offerId, Throwable cause) {
        super(offerId, cause);
    }

    public OfferPhotoReadException(Offer offer) {
        super(offer);
    }

    public OfferPhotoReadException(String offerId) {
        super(offerId);
    }

}