package org.kepennar.sharemouz.backend.reservation.services;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by kepennar on 02/08/14.
 */
public interface ReservationsService {
    Optional<Reservation> findById(String id);

    Page<Reservation> find(Pageable pageable);

    void deleteById(String id);

    Reservation reserve(Offer offer, Reservation reservation);

    Page<Reservation> findByOffer(Offer offer, Pageable pageable);
}
