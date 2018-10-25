package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.models.Laboratory;

public class LaboratoryConverter {

    public static Laboratory laboratoryEntityToModel(LaboratoryEntity laboratoryEntity) {
        Laboratory laboratory = new Laboratory();
        laboratory.setCode(laboratoryEntity.getCode());
        laboratory.setName(laboratoryEntity.getName());        
        return laboratory;
    }

    public static LaboratoryEntity laboratoryModelToEntity(Laboratory laboratory) {
        LaboratoryEntity laboratoryEntity = new LaboratoryEntity();
        laboratoryEntity.setCode(laboratory.getCode());
        laboratoryEntity.setName(laboratory.getName());        
        return laboratoryEntity;
    }

}
