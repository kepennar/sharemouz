package org.kepennar.sharemouz.backend.offer.hateoas;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.web.OfferPhotoController;
import org.kepennar.sharemouz.backend.offer.web.RestOfferController;
import org.kepennar.sharemouz.backend.profile.web.RestProfileController;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.reservation.web.RestReservationController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OfferResourceAssembler implements ResourceAssembler<Offer, OfferResource> {

    public static final String PHOTO_REL = "photo";
    public static final String PROFILE_REL = "profile";
    public static final String DELETE_REL = "delete";
    public static final String UPDATE_REL = "update";
    public static final String RESERVE_REL = "reserve";
    public static final String LIST_RESERVATIONS_REL = "list-reservations";

    private final PagedResourcesAssembler<Reservation> pageAssembler;

    @Inject
    public OfferResourceAssembler(PagedResourcesAssembler<Reservation> pagedResourcesAssembler) {
        this.pageAssembler = pagedResourcesAssembler;
    }

    @Override
    public OfferResource toResource(Offer offer) {
        try {
            String offerId = offer.getId();
            String userId = offer.getCreatedBy().getId();
            OfferResource offerResource = new OfferResource(offer);

            offerResource.add(linkTo(methodOn(RestOfferController.class).getOffer(offerId)).withSelfRel());
            offerResource.add(linkTo(methodOn(RestOfferController.class).deleteOffer(offerId)).withRel(DELETE_REL));
            offerResource.add(linkTo(RestOfferController.class).slash(offerId).withRel(UPDATE_REL));
            if (offer.isOfferPhotoImported()) {
                offerResource.add(linkTo(methodOn(OfferPhotoController.class).loadOfferPhoto(offer.getId())).withRel(PHOTO_REL));
            }

            offerResource.add(linkTo(methodOn(RestProfileController.class).getProfile(userId)).withRel(PROFILE_REL));

            offerResource.add(linkTo(RestReservationController.class).slash(offerId).slash("reserve").withRel(RESERVE_REL));
            Link reservationsLink = linkTo(methodOn(RestReservationController.class).getReservationsForOffer(offerId, null, null)).withRel(LIST_RESERVATIONS_REL);
            offerResource.add(pageAssembler.appendPaginationParameterTemplates(reservationsLink));


            return offerResource;

        } catch (Exception throwable) {
            throw new RuntimeException(throwable);
        }
    }

}