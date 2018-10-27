package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.models.Laboratory;

public class LaboratoryConverter {

    public static Laboratory laboratoryEntityToModel(LaboratoryEntity laboratoryEntity) {
        Laboratory laboratory = new Laboratory();
        laboratory.setCode(laboratoryEntity.getCode());
        laboratory.setName(laboratoryEntity.getName());        
        laboratory.setAddress(laboratoryEntity.getAddress());
        laboratory.setEmail(laboratoryEntity.getEmail());
        laboratory.setPhone(laboratoryEntity.getPhone());        
        return laboratory;
    }

    public static LaboratoryEntity laboratoryModelToEntity(Laboratory laboratory) {
        LaboratoryEntity laboratoryEntity = new LaboratoryEntity();
        laboratoryEntity.setCode(laboratory.getCode());
        laboratoryEntity.setName(laboratory.getName());        
        laboratoryEntity.setAddress(laboratory.getAddress());        
        laboratoryEntity.setEmail(laboratory.getEmail());
        laboratoryEntity.setPhone(laboratory.getPhone());
        return laboratoryEntity;
    }

}