package org.kepennar.sharemouz.backend.offer.services;

import org.kepennar.sharemouz.backend.offer.events.OfferEvent;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.repository.OfferRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by kepennar on 03/08/14.
 */
@Service
public class OffersService implements ApplicationEventPublisherAware {

    private final OfferRepository repo;

    private ApplicationEventPublisher eventsPublisher;

    @Inject
    public OffersService(OfferRepository repo) {
        this.repo = repo;
    }

    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(repo.findOne(id));
    }

    public Page<Offer> find(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public void deleteById(String id) {
        repo.delete(id);
    }

    public Offer create(Offer offer) {
        Offer savedOffer= repo.save(offer);
        this.eventsPublisher.publishEvent(new OfferEvent(savedOffer, OfferEvent.Action.CREATE));
        return savedOffer;
    }

    public Offer update(Offer offer, Offer updated) {
        offer.updateProperties(updated);
        return repo.save(offer);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventsPublisher= applicationEventPublisher;
    }
}
