package org.kepennar.sharemouz.backend.reservation;

import org.kepennar.sharemouz.backend.offer.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kepennar on 02/08/14.
 */
@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByOffer(Offer offer);
}
