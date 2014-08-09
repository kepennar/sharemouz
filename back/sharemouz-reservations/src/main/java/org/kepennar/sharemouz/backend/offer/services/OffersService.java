package org.kepennar.sharemouz.backend.offer.services;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by kepennar on 03/08/14.
 */
public interface OffersService {

    Optional<Offer> findById(String id);

    Page<Offer> find(Pageable pageable);

    void deleteById(String id);

    Offer create(Offer offer);

    Optional<Offer> update(Offer offer);

}
