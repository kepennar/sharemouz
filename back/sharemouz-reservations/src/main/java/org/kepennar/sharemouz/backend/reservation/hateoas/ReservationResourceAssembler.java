package org.kepennar.sharemouz.backend.reservation.hateoas;

import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.reservation.web.RestReservationController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ReservationResourceAssembler implements ResourceAssembler<Reservation, Resource<Reservation>> {

    @Override
    public Resource<Reservation> toResource(Reservation r) {
        try {

            String reservationId = r.getId();
            Resource<Reservation> reservationResource = new Resource<Reservation>(r);
            Collection<Link> links = new ArrayList<>();
            links.add(linkTo(methodOn(RestReservationController.class).getReservation(reservationId)).withSelfRel());

            for (Link l : links) {
                reservationResource.add(l);
            }
            return reservationResource;

        } catch (Exception throwable) {
            throw new RuntimeException(throwable);
        }
    }

}