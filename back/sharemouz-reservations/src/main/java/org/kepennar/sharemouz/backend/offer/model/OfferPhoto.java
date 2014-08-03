package org.kepennar.sharemouz.backend.offer.model;

import org.springframework.http.MediaType;

/**
 * Created by kepennar on 03/08/14.
 */
public class OfferPhoto {
    private String offerId;
    private byte[] photo;
    private MediaType mediaType;

    public OfferPhoto(String offerId, byte[] photo, MediaType mediaType) {
        this.offerId = offerId;
        this.photo = photo;
        this.mediaType = mediaType;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
