package com.pharmacy.rest.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLaboratoryEntity is a Querydsl query type for LaboratoryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLaboratoryEntity extends EntityPathBase<LaboratoryEntity> {

    private static final long serialVersionUID = 1106667447L;

    public static final QLaboratoryEntity laboratoryEntity = new QLaboratoryEntity("laboratoryEntity");

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public QLaboratoryEntity(String variable) {
        super(LaboratoryEntity.class, forVariable(variable));
    }

    public QLaboratoryEntity(Path<? extends LaboratoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLaboratoryEntity(PathMetadata metadata) {
        super(LaboratoryEntity.class, metadata);
    }

}

