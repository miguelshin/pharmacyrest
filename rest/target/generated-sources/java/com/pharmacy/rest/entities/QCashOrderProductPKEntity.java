package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashOrderProductPKEntity is a Querydsl query type for CashOrderProductPKEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCashOrderProductPKEntity extends BeanPath<CashOrderProductPKEntity> {

    private static final long serialVersionUID = -885666925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashOrderProductPKEntity cashOrderProductPKEntity = new QCashOrderProductPKEntity("cashOrderProductPKEntity");

    public final QCashOrderEntity cashorder;

    public final QProductEntity product;

    public QCashOrderProductPKEntity(String variable) {
        this(CashOrderProductPKEntity.class, forVariable(variable), INITS);
    }

    public QCashOrderProductPKEntity(Path<? extends CashOrderProductPKEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashOrderProductPKEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashOrderProductPKEntity(PathMetadata metadata, PathInits inits) {
        this(CashOrderProductPKEntity.class, metadata, inits);
    }

    public QCashOrderProductPKEntity(Class<? extends CashOrderProductPKEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cashorder = inits.isInitialized("cashorder") ? new QCashOrderEntity(forProperty("cashorder"), inits.get("cashorder")) : null;
        this.product = inits.isInitialized("product") ? new QProductEntity(forProperty("product"), inits.get("product")) : null;
    }

}

