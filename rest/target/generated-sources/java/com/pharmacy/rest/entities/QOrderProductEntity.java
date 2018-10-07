package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProductEntity is a Querydsl query type for OrderProductEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderProductEntity extends EntityPathBase<OrderProductEntity> {

    private static final long serialVersionUID = 238605061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductEntity orderProductEntity = new QOrderProductEntity("orderProductEntity");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final QOrderProductPKEntity id;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QOrderProductEntity(String variable) {
        this(OrderProductEntity.class, forVariable(variable), INITS);
    }

    public QOrderProductEntity(Path<? extends OrderProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProductEntity(PathMetadata metadata, PathInits inits) {
        this(OrderProductEntity.class, metadata, inits);
    }

    public QOrderProductEntity(Class<? extends OrderProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QOrderProductPKEntity(forProperty("id"), inits.get("id")) : null;
    }

}

