package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashOrderProductEntity is a Querydsl query type for CashOrderProductEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCashOrderProductEntity extends EntityPathBase<CashOrderProductEntity> {

    private static final long serialVersionUID = -1019553000L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashOrderProductEntity cashOrderProductEntity = new QCashOrderProductEntity("cashOrderProductEntity");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final QCashOrderProductPKEntity id;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QCashOrderProductEntity(String variable) {
        this(CashOrderProductEntity.class, forVariable(variable), INITS);
    }

    public QCashOrderProductEntity(Path<? extends CashOrderProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashOrderProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashOrderProductEntity(PathMetadata metadata, PathInits inits) {
        this(CashOrderProductEntity.class, metadata, inits);
    }

    public QCashOrderProductEntity(Class<? extends CashOrderProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QCashOrderProductPKEntity(forProperty("id"), inits.get("id")) : null;
    }

}

