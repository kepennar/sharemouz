package org.kepennar.sharemouz.backend.offer.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QOffer is a Querydsl query type for Offer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOffer extends EntityPathBase<Offer> {

    private static final long serialVersionUID = -1552510689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOffer offer = new QOffer("offer");

    public final org.kepennar.sharemouz.backend.model.QAbstractDocument _super;

    //inherited
    public final DateTimePath<java.time.Instant> createdAt;

    // inherited
    protected org.kepennar.sharemouz.backend.model.QUser createdBy;

    public final StringPath description = createString("description");

    //inherited
    public final StringPath id;

    //inherited
    public final DateTimePath<java.time.Instant> lastModified;

    // inherited
    protected org.kepennar.sharemouz.backend.model.QUser lastModifiedBy;

    public final StringPath name = createString("name");

    public final BooleanPath offerPhotoImported = createBoolean("offerPhotoImported");

    public final StringPath offerPhotoMediaType = createString("offerPhotoMediaType");

    //inherited
    public final NumberPath<Long> version;

    public QOffer(String variable) {
        this(Offer.class, forVariable(variable), INITS);
    }

    public QOffer(Path<? extends Offer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOffer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOffer(PathMetadata<?> metadata, PathInits inits) {
        this(Offer.class, metadata, inits);
    }

    public QOffer(Class<? extends Offer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new org.kepennar.sharemouz.backend.model.QAbstractDocument(type, metadata, inits);
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.lastModified = _super.lastModified;
        this.version = _super.version;
    }

    public org.kepennar.sharemouz.backend.model.QUser createdBy() {
        if (createdBy == null) {
            createdBy = new org.kepennar.sharemouz.backend.model.QUser(forProperty("createdBy"));
        }
        return createdBy;
    }

    public org.kepennar.sharemouz.backend.model.QUser lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new org.kepennar.sharemouz.backend.model.QUser(forProperty("lastModifiedBy"));
        }
        return lastModifiedBy;
    }

}

