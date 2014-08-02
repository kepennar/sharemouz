package org.kepennar.sharemouz.backend.offer;

import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;
import org.kepennar.sharemouz.backend.model.AbstractDocument;
import org.springframework.util.StringUtils;

/**
 * Created by kepennar on 02/08/14.
 */
public class Offer extends AbstractDocument {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    public Offer() {
    }

    public Offer(String name, String description) {
        this.name = name;
        this.description = description;
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

    public void updateProperties(Offer source) {
        if (!StringUtils.isEmpty(source.getName())) {
            this.setName(source.getName());
        }
        if (!StringUtils.isEmpty(source.getDescription())) {
            this.setDescription(source.getDescription());
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
