package org.kepennar.sharemouz.backend.offer.exception;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class OfferPhotoWriteException extends OfferException {

	private static final long serialVersionUID = 1L;

	public OfferPhotoWriteException(Offer offer, Throwable cause) {
        super(offer, cause);
	}

	public OfferPhotoWriteException(String offerId, Throwable cause) {
		super(offerId, cause);
	}

	public OfferPhotoWriteException(Offer offer) {
		super(offer);
	}

	public OfferPhotoWriteException(String offerId) {
		super(offerId);
	}

}