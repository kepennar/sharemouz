package org.kepennar.sharemouz.backend.offer.events;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.context.ApplicationEvent;
/**
 * Created by kepennar on 15/08/14.
 */
public class OfferEvent extends ApplicationEvent {
    private Action action;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public OfferEvent(Offer source, Action action) {
        super(source);
        this.action= action;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public Offer getSource() {
        return (Offer) super.getSource();
    }

    public Action getAction() {
        return action;
    }

    public static enum Action {
        CREATE,
        CANCEL;
    }
}
