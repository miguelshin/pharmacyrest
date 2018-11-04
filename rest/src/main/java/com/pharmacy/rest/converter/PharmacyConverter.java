package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.models.Pharmacy;

public class PharmacyConverter {

    public static Pharmacy pharmacyEntityToModel(PharmacyEntity pharmacyEntity) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setCode(pharmacyEntity.getCode());
        pharmacy.setName(pharmacyEntity.getName());
        pharmacy.setAddress(pharmacyEntity.getAddress());
        pharmacy.setCif(pharmacyEntity.getCif());
        pharmacy.setEmail(pharmacyEntity.getEmail());
        pharmacy.setPhone(pharmacyEntity.getPhone());
        //pharmacy.setUserCode(pharmacyEntity.getUserCode());
        
        return pharmacy;
    }

    public static PharmacyEntity pharmacyModelToEntity(Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = new PharmacyEntity();
        pharmacyEntity.setCode(pharmacy.getCode());
        pharmacyEntity.setName(pharmacy.getName());
        pharmacyEntity.setAddress(pharmacy.getAddress());
        pharmacyEntity.setCif(pharmacy.getCif());
        pharmacyEntity.setEmail(pharmacy.getEmail());
        pharmacyEntity.setPhone(pharmacy.getPhone());
        pharmacyEntity.setUserCode(pharmacy.getUserCode());
        
        return pharmacyEntity;
    }

}
