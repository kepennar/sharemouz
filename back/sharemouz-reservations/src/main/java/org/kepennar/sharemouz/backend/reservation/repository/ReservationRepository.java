package org.kepennar.sharemouz.backend.reservation.repository;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kepennar on 02/08/14.
 */
@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Page<Reservation> findByOffer(Offer offer, Pageable pageable);
}
