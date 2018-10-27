package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashOrderEntity is a Querydsl query type for CashOrderEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCashOrderEntity extends EntityPathBase<CashOrderEntity> {

    private static final long serialVersionUID = -1370251971L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashOrderEntity cashOrderEntity = new QCashOrderEntity("cashOrderEntity");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final StringPath observations = createString("observations");

    public final QPharmacyEntity pharmacy;

    public QCashOrderEntity(String variable) {
        this(CashOrderEntity.class, forVariable(variable), INITS);
    }

    public QCashOrderEntity(Path<? extends CashOrderEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashOrderEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashOrderEntity(PathMetadata metadata, PathInits inits) {
        this(CashOrderEntity.class, metadata, inits);
    }

    public QCashOrderEntity(Class<? extends CashOrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pharmacy = inits.isInitialized("pharmacy") ? new QPharmacyEntity(forProperty("pharmacy")) : null;
    }

}

