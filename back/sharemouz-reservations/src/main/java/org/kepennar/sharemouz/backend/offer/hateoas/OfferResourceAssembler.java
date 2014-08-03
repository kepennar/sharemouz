package org.kepennar.sharemouz.backend.offer.hateoas;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.web.OfferPhotoController;
import org.kepennar.sharemouz.backend.offer.web.RestOfferController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OfferResourceAssembler implements ResourceAssembler<Offer, Resource<Offer>> {

    public static final String PHOTO_REL = "photo";

    @Override
    public Resource<Offer> toResource(Offer u) {
        try {
            Offer offer = new Offer(u);

            String offerId = offer.getId();
            Resource<Offer> offerResource = new Resource<Offer>(offer);
            Collection<Link> links = new ArrayList<>();
            links.add(linkTo(methodOn(RestOfferController.class).getOffer(offerId)).withSelfRel());
            if (offer.isOfferPhotoImported()) {
                links.add(linkTo(methodOn(OfferPhotoController.class).loadOfferPhoto(offer.getId())).withRel(PHOTO_REL));
            }
            for (Link l : links) {
                offerResource.add(l);
            }
            return offerResource;

        } catch (Exception throwable) {
            throw new RuntimeException(throwable);
        }
    }

}