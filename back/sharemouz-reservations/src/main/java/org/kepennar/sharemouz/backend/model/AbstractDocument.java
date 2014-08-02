package org.kepennar.sharemouz.backend.model;

import org.kepennar.sharemouz.backend.config.audit.AuditableUser;
import org.springframework.data.annotation.*;

import java.time.Instant;

/**
 * Created by kepennar on 02/08/14.
 */
public class AbstractDocument {

    @Id
    private String id;
    @Version
    private Long version;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant lastModified;

    @CreatedBy
    private AuditableUser createdBy;
    @LastModifiedBy
    private AuditableUser lastModifiedBy;

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

    public AuditableUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AuditableUser createdBy) {
        this.createdBy = createdBy;
    }

    public AuditableUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(AuditableUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}
