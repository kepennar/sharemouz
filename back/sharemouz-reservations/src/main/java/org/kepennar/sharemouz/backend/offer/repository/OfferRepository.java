package org.kepennar.sharemouz.backend.offer.repository;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by kepennar on 02/08/14.
 */
@Repository
public interface OfferRepository extends MongoRepository<Offer, String>, QueryDslPredicateExecutor<Offer> {
    @Query("{ createdBy._id: { $oid : ?0}}")
    public Page<Offer> findByProfilId(String id, Pageable pageable);
}
