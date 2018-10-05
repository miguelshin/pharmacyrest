package com.pharmacy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pharmacy.converter.PharmacyConverter;
import com.pharmacy.entities.PharmacyEntity;
import com.pharmacy.models.Pharmacy;
import com.pharmacy.repositories.pharmacy.PharmacyJpaRepository;
import com.pharmacy.repositories.pharmacy.PharmacyQueryDSLRepository;

@Service("pharmacyService")
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    @Qualifier("pharmacyJpaRepository")
    private PharmacyJpaRepository pharmacyJpaRepository;

    @Autowired
    @Qualifier("pharmacyQueryDSLRepository")
    private PharmacyQueryDSLRepository pharmacyQueryDSLRepository;

    @Override
    public List<Pharmacy> searchPharmacies(String textName) {
        List<PharmacyEntity> pharmacyEntities = pharmacyQueryDSLRepository.searchPharmacies(textName);
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        for (PharmacyEntity pharmacyEntity : pharmacyEntities) {
            pharmacies.add(PharmacyConverter.pharmacyEntityToModel(pharmacyEntity));
        }
        return pharmacies;
    }
    
    @Override
    public Pharmacy getPharmacy(String code) {
        PharmacyEntity pharmacyEntity = pharmacyJpaRepository.findByCode(code);
        Pharmacy pharmacy = PharmacyConverter.pharmacyEntityToModel(pharmacyEntity);
        return pharmacy;
    }
    
    @Override
    public Pharmacy savePharmacy(Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = PharmacyConverter.pharmacyModelToEntity(pharmacy);
        pharmacyJpaRepository.save(pharmacyEntity);
        return getPharmacy(pharmacyEntity.getCode());
    }

}
