package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProductPKEntity is a Querydsl query type for OrderProductPKEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QOrderProductPKEntity extends BeanPath<OrderProductPKEntity> {

    private static final long serialVersionUID = 1318419520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductPKEntity orderProductPKEntity = new QOrderProductPKEntity("orderProductPKEntity");

    public final QOrderEntity cashorder;

    public final QProductEntity product;

    public QOrderProductPKEntity(String variable) {
        this(OrderProductPKEntity.class, forVariable(variable), INITS);
    }

    public QOrderProductPKEntity(Path<? extends OrderProductPKEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProductPKEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProductPKEntity(PathMetadata metadata, PathInits inits) {
        this(OrderProductPKEntity.class, metadata, inits);
    }

    public QOrderProductPKEntity(Class<? extends OrderProductPKEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cashorder = inits.isInitialized("cashorder") ? new QOrderEntity(forProperty("cashorder"), inits.get("cashorder")) : null;
        this.product = inits.isInitialized("product") ? new QProductEntity(forProperty("product"), inits.get("product")) : null;
    }

}

