package com.pharmacy.converter;

import com.pharmacy.entities.PharmacyEntity;
import com.pharmacy.models.Pharmacy;

public class PharmacyConverter {

    public static Pharmacy pharmacyEntityToModel(PharmacyEntity pharmacyEntity) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setCode(pharmacyEntity.getCode());
        pharmacy.setName(pharmacyEntity.getName());
        pharmacy.setAddress(pharmacyEntity.getAddress());
        pharmacy.setCif(pharmacyEntity.getCif());
        
        return pharmacy;
    }

    public static PharmacyEntity pharmacyModelToEntity(Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = new PharmacyEntity();
        pharmacyEntity.setCode(pharmacy.getCode());
        pharmacyEntity.setName(pharmacy.getName());
        pharmacyEntity.setAddress(pharmacy.getAddress());
        pharmacyEntity.setCif(pharmacy.getCif());
        
        return pharmacyEntity;
    }

}
