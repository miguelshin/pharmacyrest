package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashOrderImageEntity is a Querydsl query type for CashOrderImageEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCashOrderImageEntity extends EntityPathBase<CashOrderImageEntity> {

    private static final long serialVersionUID = -1057236988L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashOrderImageEntity cashOrderImageEntity = new QCashOrderImageEntity("cashOrderImageEntity");

    public final QCashOrderImagePKEntity id;

    public QCashOrderImageEntity(String variable) {
        this(CashOrderImageEntity.class, forVariable(variable), INITS);
    }

    public QCashOrderImageEntity(Path<? extends CashOrderImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashOrderImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashOrderImageEntity(PathMetadata metadata, PathInits inits) {
        this(CashOrderImageEntity.class, metadata, inits);
    }

    public QCashOrderImageEntity(Class<? extends CashOrderImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QCashOrderImagePKEntity(forProperty("id"), inits.get("id")) : null;
    }

}

