package org.kepennar.sharemouz.backend.offer.hateoas;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.hateoas.ResourceSupport;

import java.time.Instant;

/**
 * Created by kepennar on 10/08/14.
 */
public class OfferResource extends ResourceSupport {
    private String name;
    private String description;
    private Long version;
    private Instant createdAt;
    private Instant lastModified;

    public OfferResource() {
    }

    public OfferResource(Offer o) {
        this.name = o.getName();
        this.description = o.getDescription();
        this.version = o.getVersion();
        this.createdAt = o.getCreatedAt();
        this.lastModified = o.getLastModified();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
