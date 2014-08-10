package org.kepennar.sharemouz.backend.reservation.hateoas;

import org.kepennar.sharemouz.backend.offer.hateoas.OfferResourceAssembler;
import org.kepennar.sharemouz.backend.profile.web.RestProfileController;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.reservation.web.RestReservationController;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ReservationResourceAssembler implements ResourceAssembler<Reservation, ReservationResource> {
    public static final String DELETE_REL = "delete";
    public static final String UPDATE_REL = "update";
    public static final String PROFILE_REL = "profile";

    private final OfferResourceAssembler offerAssembler;

    @Inject
    public ReservationResourceAssembler(OfferResourceAssembler offerResourceAssembler) {
        this.offerAssembler= offerResourceAssembler;
    }

    @Override
    public ReservationResource toResource(Reservation r) {
        try {

            String reservationId = r.getId();
            String userId = r.getCreatedBy().getId();
            ReservationResource reservationResource = new ReservationResource(r, offerAssembler.toResource(r.getOffer()));

            reservationResource.add(linkTo(methodOn(RestReservationController.class).getReservation(reservationId)).withSelfRel());
            reservationResource.add(linkTo(methodOn(RestReservationController.class).deleteReservation(reservationId)).withRel(DELETE_REL));
            reservationResource.add(linkTo(methodOn(RestProfileController.class).getProfile(userId)).withRel(PROFILE_REL));

            return reservationResource;

        } catch (Exception throwable) {
            throw new RuntimeException(throwable);
        }
    }

}