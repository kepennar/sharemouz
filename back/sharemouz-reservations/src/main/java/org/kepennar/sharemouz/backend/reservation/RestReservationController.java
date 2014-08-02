package org.kepennar.sharemouz.backend.reservation;

import org.kepennar.sharemouz.backend.model.ValidationError;
import org.kepennar.sharemouz.backend.offer.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kepennar on 02/08/14.
 */
@RestController
@RequestMapping("/api/reservations")
public class RestReservationController {
    private final ReservationRepository repo;

    @Inject
    public RestReservationController(ReservationRepository reservationRepository) {
        this.repo = reservationRepository;
    }

    @RequestMapping(method = GET, value = "{id}")
    public HttpEntity<Reservation> getReservation(@PathVariable String id) {
        HttpEntity<Reservation> httpEntity = new ResponseEntity<>(NOT_FOUND);
        return Optional.ofNullable(repo.findOne(id))
                .map(r -> {
                    return new ResponseEntity<>(r, OK);
                })
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(method = DELETE, value = "{id}")
    public HttpEntity<Reservation> deleteReservation(@PathVariable String id) {
        HttpEntity<Reservation> httpEntity = new ResponseEntity<>(NOT_FOUND);
        repo.delete(id);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(method = PUT)
    public HttpEntity<Reservation> createReservation(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }

        return new ResponseEntity<>(repo.save(reservation), OK);
    }

    @RequestMapping(method = PUT, value = "reserve")
    public HttpEntity<Reservation> reserve(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }

        return new ResponseEntity<>(repo.save(reservation), OK);
    }

    @RequestMapping(method = GET, value = "offer/{offerId}")
    public HttpEntity<List<Reservation>> reserve(@PathVariable String offerId) {
        Offer offer = new Offer();
        offer.setId(offerId);
        List<Reservation> reservations = repo.findByOffer(offer);
        return new ResponseEntity<>(reservations, OK);
    }


}
