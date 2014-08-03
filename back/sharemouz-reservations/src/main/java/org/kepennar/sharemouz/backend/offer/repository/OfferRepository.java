package org.kepennar.sharemouz.backend.offer.repository;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kepennar on 02/08/14.
 */
@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
}
