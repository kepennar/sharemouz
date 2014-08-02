package org.kepennar.sharemouz.backend.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kepennar on 02/08/14.
 */
@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
}
