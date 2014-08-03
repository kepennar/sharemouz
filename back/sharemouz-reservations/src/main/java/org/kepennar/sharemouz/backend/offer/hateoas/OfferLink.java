package org.kepennar.sharemouz.backend.offer.hateoas;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kepennar on 03/08/14.
 */
@Component
public class OfferLink {
    private static final String PHOTO = "photo";
    private static final String PHOTO_REL = "photo";

    private final EntityLinks entityLinks;

    @Inject
    public OfferLink(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    Link getSelfLink(Offer offer) {
        return this.entityLinks.linkForSingleResource(Offer.class, offer.getId()).withSelfRel();
    }

    Link getPhotoLink(Offer offer) {
        return this.entityLinks.linkForSingleResource(Offer.class, offer.getId()).slash(PHOTO).withRel(PHOTO_REL);
    }
}
