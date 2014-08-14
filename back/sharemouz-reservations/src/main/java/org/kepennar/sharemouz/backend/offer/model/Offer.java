package org.kepennar.sharemouz.backend.offer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;
import org.kepennar.sharemouz.backend.model.AbstractDocument;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by kepennar on 02/08/14.
 */
@Document
public class Offer extends AbstractDocument {

    @NotEmpty
    @Indexed
    private String name;

    @NotEmpty
    private String description;

    private String offerPhotoMediaType;

    @JsonIgnore
    private boolean offerPhotoImported;

    public Offer() {
    }

    public Offer(String id) {
        super(id);
    }

    public Offer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Offer(String name, String description, String photoMediaType) {
        this.name = name;
        this.description = description;
        this.offerPhotoMediaType = photoMediaType;
        if (!isEmpty(this.offerPhotoMediaType)) {
            this.offerPhotoImported = true;
        }
    }

    public Offer(String id, String name, String description, String photoMediaType) {
        this(name, description, photoMediaType);
        this.setId(id);

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

    public String getOfferPhotoMediaType() {
        return offerPhotoMediaType;
    }

    public void setOfferPhotoMediaType(String offerPhotoMediaType) {
        this.offerPhotoMediaType = offerPhotoMediaType;
    }

    public boolean isOfferPhotoImported() {
        return offerPhotoImported;
    }

    public void setOfferPhotoImported(boolean offerPhotoImported) {
        this.offerPhotoImported = offerPhotoImported;
    }

    public void updateProperties(Offer source) {
        if (!isEmpty(source.getName())) {
            this.setName(source.getName());
        }
        if (!isEmpty(source.getDescription())) {
            this.setDescription(source.getDescription());
        }
        if (!isEmpty(source.getOfferPhotoMediaType())) {
            this.setOfferPhotoMediaType(source.getOfferPhotoMediaType());
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Offer.class)
                .add("name", this.name)
                .add("description", this.description)
                .toString();
    }
}
