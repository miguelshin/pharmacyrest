package com.pharmacy.rest.services.laboratory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.LaboratoryConverter;
import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.models.Laboratory;
import com.pharmacy.rest.repositories.laboratory.LaboratoryJpaRepository;
import com.pharmacy.rest.repositories.laboratory.LaboratoryQueryDSLRepository;
import com.pharmacy.rest.repositories.pharmacy.PharmacyQueryDSLRepository;

@Service("laboratoryService")
public class LaboratoryServiceImpl implements LaboratoryService {
    @Autowired
    @Qualifier("laboratoryJpaRepository")
    private LaboratoryJpaRepository laboratoryJpaRepository;

    @Autowired
    @Qualifier("laboratoryQueryDSLRepository")
    private LaboratoryQueryDSLRepository laboratoryQueryDSLRepository;

    @Override
    public List<Laboratory> searchLaboratories(String textName) {
    	String userCode = getUserCodeFromAuthentication();
        List<LaboratoryEntity> laboratoryEntities = laboratoryQueryDSLRepository.searchLaboratories(textName, userCode);
        List<Laboratory> laboratories = new ArrayList<Laboratory>();
        for (LaboratoryEntity laboratoryEntity : laboratoryEntities) {
            laboratories.add(LaboratoryConverter.laboratoryEntityToModel(laboratoryEntity));
        }
        return laboratories;
    }

    @Override
    public Laboratory getLaboratory(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	LaboratoryEntity laboratoryEntity = laboratoryJpaRepository.findByCodeAndUserCode(code, userCode);
        Laboratory laboratory = LaboratoryConverter.laboratoryEntityToModel(laboratoryEntity);
        return laboratory;
    }
    
    @Override
    public Laboratory saveLaboratory(Laboratory laboratory) {
    	String userCode = getUserCodeFromAuthentication();
        LaboratoryEntity laboratoryEntity = LaboratoryConverter.laboratoryModelToEntity(laboratory);
        laboratoryEntity.setUserCode(userCode);
        laboratoryJpaRepository.save(laboratoryEntity);
        return getLaboratory(laboratoryEntity.getCode());
    }

    @Override
    public Laboratory updateLaboratory(Laboratory laboratory) {
    	String userCode = getUserCodeFromAuthentication();
    	LaboratoryEntity laboratoryEntity = laboratoryJpaRepository.findByCodeAndUserCode(laboratory.getCode(), userCode);
    	if (laboratoryEntity != null) {
	        laboratoryJpaRepository.save(laboratoryEntity);
	        return getLaboratory(laboratoryEntity.getCode());
    	}
    	return null;
    }
    
    @Override
    public boolean deleteLaboratory(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	LaboratoryEntity laboratoryEntity = this.laboratoryJpaRepository.findByCodeAndUserCode(code, userCode);
    	if (laboratoryEntity != null) {
    		laboratoryJpaRepository.delete(laboratoryEntity);
    		return true;
    	}
    	return false;
    }

    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
