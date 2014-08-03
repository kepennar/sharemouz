package org.kepennar.sharemouz.backend.reservation.web;

import org.kepennar.sharemouz.backend.model.ValidationError;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.hateoas.ReservationResourceAssembler;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.reservation.services.ReservationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.kepennar.sharemouz.backend.ApiUrls.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kepennar on 02/08/14.
 */
@RestController
@RequestMapping(ROOT_URL_RESERVATIONS)
public class RestReservationController {
    private final ReservationsService service;
    private final ReservationResourceAssembler assembler;

    @Inject
    public RestReservationController(ReservationsService reservationsService, ReservationResourceAssembler reservationResourceAssembler) {
        this.service = reservationsService;
        this.assembler = reservationResourceAssembler;
    }

    @RequestMapping(method = GET, value = URL_RESERVATIONS_RESERVATION)
    public HttpEntity<Resource<Reservation>> getReservation(@PathVariable String id) {
        return service.findById(id)
                .map(r -> {
                    return new ResponseEntity<>(assembler.toResource(r), OK);
                })
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(method = DELETE, value = URL_RESERVATIONS_RESERVATION)
    public HttpEntity<Reservation> deleteReservation(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(method = PUT)
    public HttpEntity<Resource<Reservation>> createReservation(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }
        Reservation createdReservation = service.create(reservation);
        return new ResponseEntity<>(assembler.toResource(createdReservation), OK);
    }

    @RequestMapping(method = PUT, value = URL_RESERVATIONS_RESERVE)
    public HttpEntity<Resource<Reservation>> reserve(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }
        Reservation reserved = service.create(reservation);
        return new ResponseEntity<>(assembler.toResource(reserved), OK);
    }

    @RequestMapping(method = GET, value = URL_RESERVATIONS_BY_OFFER, params = {"page", "size"})
    public HttpEntity<PagedResources<Resource<Reservation>>> byOffer(@PathVariable String offerId, Pageable pageable, PagedResourcesAssembler<Reservation> pagedAssembler) {
        Offer offer = new Offer(offerId);
        Page<Reservation> reservations = service.findByOffer(offer, pageable);

        return new ResponseEntity<>(pagedAssembler.toResource(reservations, assembler), OK);
    }


}