package org.kepennar.sharemouz.backend.model;

import org.springframework.data.annotation.*;

import java.time.Instant;

/**
 * Created by kepennar on 02/08/14.
 */
public class AbstractDocument {

    public AbstractDocument() {}

    public AbstractDocument(String id) {
        this.id = id;
    }

    @Id
    private String id;

    @Version
    private Long version;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant lastModified;

    @CreatedBy
    private User createdBy;

    @LastModifiedBy
    private User lastModifiedBy;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
