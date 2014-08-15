package org.kepennar.sharemouz.backend.profile.events;

import org.kepennar.sharemouz.backend.offer.events.OfferEvent;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.profile.services.ProfilService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kepennar on 15/08/14.
 */
@Component
public class OfferEventListener implements ApplicationListener<OfferEvent> {
    private final ProfilService service;
    @Inject
    public OfferEventListener(ProfilService profilService) {this.service= profilService;}

    @Override
    public void onApplicationEvent(OfferEvent event) {
        Offer offer = event.getSource();
        service.updateOffersCounter(offer.getCreatedBy(), event.getAction());
    }
}
