package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPharmacyEntity is a Querydsl query type for PharmacyEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPharmacyEntity extends EntityPathBase<PharmacyEntity> {

    private static final long serialVersionUID = -1844242185L;

    public static final QPharmacyEntity pharmacyEntity = new QPharmacyEntity("pharmacyEntity");

    public final StringPath address = createString("address");

    public final StringPath cif = createString("cif");

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public final StringPath userCode = createString("userCode");

    public QPharmacyEntity(String variable) {
        super(PharmacyEntity.class, forVariable(variable));
    }

    public QPharmacyEntity(Path<? extends PharmacyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPharmacyEntity(PathMetadata metadata) {
        super(PharmacyEntity.class, metadata);
    }

}

