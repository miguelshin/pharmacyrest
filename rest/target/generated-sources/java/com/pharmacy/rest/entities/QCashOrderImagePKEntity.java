package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashOrderImagePKEntity is a Querydsl query type for CashOrderImagePKEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCashOrderImagePKEntity extends BeanPath<CashOrderImagePKEntity> {

    private static final long serialVersionUID = 1554726271L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashOrderImagePKEntity cashOrderImagePKEntity = new QCashOrderImagePKEntity("cashOrderImagePKEntity");

    public final QCashOrderEntity cashOrder;

    public final StringPath url = createString("url");

    public QCashOrderImagePKEntity(String variable) {
        this(CashOrderImagePKEntity.class, forVariable(variable), INITS);
    }

    public QCashOrderImagePKEntity(Path<? extends CashOrderImagePKEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashOrderImagePKEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashOrderImagePKEntity(PathMetadata metadata, PathInits inits) {
        this(CashOrderImagePKEntity.class, metadata, inits);
    }

    public QCashOrderImagePKEntity(Class<? extends CashOrderImagePKEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cashOrder = inits.isInitialized("cashOrder") ? new QCashOrderEntity(forProperty("cashOrder"), inits.get("cashOrder")) : null;
    }

}

