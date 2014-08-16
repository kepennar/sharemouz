package org.kepennar.sharemouz.backend.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QAbstractDocument is a Querydsl query type for AbstractDocument
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QAbstractDocument extends BeanPath<AbstractDocument> {

    private static final long serialVersionUID = -816576152L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAbstractDocument abstractDocument = new QAbstractDocument("abstractDocument");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    protected QUser createdBy;

    public final StringPath id = createString("id");

    public final DateTimePath<java.time.Instant> lastModified = createDateTime("lastModified", java.time.Instant.class);

    protected QUser lastModifiedBy;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QAbstractDocument(String variable) {
        this(AbstractDocument.class, forVariable(variable), INITS);
    }

    public QAbstractDocument(Path<? extends AbstractDocument> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAbstractDocument(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAbstractDocument(PathMetadata<?> metadata, PathInits inits) {
        this(AbstractDocument.class, metadata, inits);
    }

    public QAbstractDocument(Class<? extends AbstractDocument> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUser(forProperty("lastModifiedBy")) : null;
    }

    public QUser createdBy() {
        if (createdBy == null) {
            createdBy = new QUser(forProperty("createdBy"));
        }
        return createdBy;
    }

    public QUser lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new QUser(forProperty("lastModifiedBy"));
        }
        return lastModifiedBy;
    }

}

