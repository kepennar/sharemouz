package org.kepennar.sharemouz.backend.reservation.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 470246975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final org.kepennar.sharemouz.backend.model.QAbstractDocument _super;

    public final DateTimePath<java.time.Instant> begin = createDateTime("begin", java.time.Instant.class);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt;

    // inherited
    protected org.kepennar.sharemouz.backend.model.QUser createdBy;

    public final DateTimePath<java.time.Instant> end = createDateTime("end", java.time.Instant.class);

    //inherited
    public final StringPath id;

    //inherited
    public final DateTimePath<java.time.Instant> lastModified;

    // inherited
    protected org.kepennar.sharemouz.backend.model.QUser lastModifiedBy;

    protected org.kepennar.sharemouz.backend.offer.model.QOffer offer;

    //inherited
    public final NumberPath<Long> version;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReservation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReservation(PathMetadata<?> metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new org.kepennar.sharemouz.backend.model.QAbstractDocument(type, metadata, inits);
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.lastModified = _super.lastModified;
        this.offer = inits.isInitialized("offer") ? new org.kepennar.sharemouz.backend.offer.model.QOffer(forProperty("offer"), inits.get("offer")) : null;
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

    public org.kepennar.sharemouz.backend.offer.model.QOffer offer() {
        if (offer == null) {
            offer = new org.kepennar.sharemouz.backend.offer.model.QOffer(forProperty("offer"));
        }
        return offer;
    }

}

