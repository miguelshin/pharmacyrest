package com.pharmacy.rest.services.pharmacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.PharmacyConverter;
import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.models.Pharmacy;
import com.pharmacy.rest.repositories.pharmacy.PharmacyJpaRepository;
import com.pharmacy.rest.repositories.pharmacy.PharmacyQueryDSLRepository;

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
    	String userCode = getUserCodeFromAuthentication();
        List<PharmacyEntity> pharmacyEntities = pharmacyQueryDSLRepository.searchPharmacies(textName, userCode);
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        for (PharmacyEntity pharmacyEntity : pharmacyEntities) {
            pharmacies.add(PharmacyConverter.pharmacyEntityToModel(pharmacyEntity));
        }
        return pharmacies;
    }

    @Override
    public PharmacyEntity checkUserAndGetPharmacyEntity(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	return pharmacyJpaRepository.findByCodeAndUserCode(code, userCode);
    }
    
    @Override
    public Optional<Pharmacy> getPharmacy(String code) {
    	PharmacyEntity pharmacyEntity = checkUserAndGetPharmacyEntity(code);
        Pharmacy pharmacy = PharmacyConverter.pharmacyEntityToModel(pharmacyEntity);
        return Optional.of(pharmacy);
    }
    
    @Override
    public Pharmacy savePharmacy(Pharmacy pharmacy) {
    	String userCode = getUserCodeFromAuthentication();
    	pharmacy.setUserCode(userCode);
        PharmacyEntity pharmacyEntity = PharmacyConverter.pharmacyModelToEntity(pharmacy);
        pharmacyJpaRepository.save(pharmacyEntity);
        return getPharmacy(pharmacyEntity.getCode()).get();
    }

    @Override
    public Optional<Pharmacy> updatePharmacy(Pharmacy pharmacy) {
    	Optional<Pharmacy> updatedPharmacy = Optional.empty();
    	PharmacyEntity pharmacyEntity = checkUserAndGetPharmacyEntity(pharmacy.getCode());
    	if (pharmacyEntity != null) {
	        pharmacyJpaRepository.save(pharmacyEntity);
	        updatedPharmacy = getPharmacy(pharmacyEntity.getCode());
    	}
    	return updatedPharmacy;
    }
    
    @Override
    public boolean deletePharmacy(String code) {
    	PharmacyEntity pharmacyEntity = checkUserAndGetPharmacyEntity(code);
    	if (pharmacyEntity != null) {
    		pharmacyJpaRepository.delete(pharmacyEntity);
    		return true;
    	}
    	return false;
    }
    
    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
