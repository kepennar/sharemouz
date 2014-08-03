package org.kepennar.sharemouz.backend.reservation.model;

import com.google.common.base.Objects;
import org.kepennar.sharemouz.backend.model.AbstractDocument;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Created by kepennar on 02/08/14.
 */
public class Reservation extends AbstractDocument {

    @NotNull
    @DBRef
    private Offer offer;

    @NotNull
    private Instant begin;
    @NotNull
    private Instant end;

    public Reservation() {
    }

    public Reservation(Offer offer, Instant begin, Instant end) {
        this.offer = offer;
        this.begin = begin;
        this.end = end;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Instant getBegin() {
        return begin;
    }

    public void setBegin(Instant begin) {
        this.begin = begin;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Reservation.class)
                .add("offer", this.offer)
                .add("begin", this.begin)
                .add("end", this.end)
                .toString();
    }
}
