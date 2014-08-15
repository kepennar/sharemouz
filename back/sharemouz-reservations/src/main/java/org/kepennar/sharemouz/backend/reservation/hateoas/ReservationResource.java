package org.kepennar.sharemouz.backend.reservation.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.kepennar.sharemouz.backend.offer.hateoas.OfferResource;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.springframework.hateoas.ResourceSupport;

import java.time.Instant;

/**
 * Created by kepennar on 10/08/14.
 */
@JsonTypeName("reservation")
public class ReservationResource extends ResourceSupport {

    @JsonProperty("offer")
    private OfferResource offerResource;
    private Instant begin;
    private Instant end;
    private Long version;
    private Instant createdAt;
    private Instant lastModified;

    public ReservationResource() { }

    public ReservationResource(Reservation r, OfferResource offer) {
        this.offerResource= offer;
        this.begin      = r.getBegin();
        this.end        = r.getEnd();
        this.version    = r.getVersion();
        this.createdAt  = r.getCreatedAt();
        this.lastModified=r.getLastModified();

    }

    public OfferResource getOfferResource() {
        return offerResource;
    }

    public void setOfferResource(OfferResource offerResource) {
        this.offerResource = offerResource;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

}
