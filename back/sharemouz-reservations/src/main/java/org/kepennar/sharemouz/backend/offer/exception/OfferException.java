package org.kepennar.sharemouz.backend.offer.exception;

import org.kepennar.sharemouz.backend.offer.model.Offer;

public class OfferException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OfferException(Offer offer) {
        this(offer.getId());
    }

    public OfferException(Offer offer, Throwable cause) {
        this(offer == null ? null : offer.getId(), cause);
    }

    public OfferException(String offerId, Throwable cause) {
        super("Could not find Offer for Offer # " + offerId, cause);
    }


    public OfferException(String offerId) {
        super("Could not find Offer for Offer # " + offerId);
    }

}