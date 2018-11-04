package com.pharmacy.rest.services.laboratory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Laboratory> getLaboratory(String code) {
    	LaboratoryEntity laboratoryEntity = checkUserAndGetLaboratoryEntity(code);
        Laboratory laboratory = LaboratoryConverter.laboratoryEntityToModel(laboratoryEntity);
        return Optional.of(laboratory);
    }
    
    @Override
    public Laboratory saveLaboratory(Laboratory laboratory) {
    	String userCode = getUserCodeFromAuthentication();
        laboratory.setUserCode(userCode);
        LaboratoryEntity laboratoryEntity = LaboratoryConverter.laboratoryModelToEntity(laboratory);
        laboratoryJpaRepository.save(laboratoryEntity);
        return getLaboratory(laboratoryEntity.getCode()).get();
    }

    @Override
    public Optional<Laboratory> updateLaboratory(Laboratory laboratory) {
    	Optional<Laboratory> updatedLaboratory = Optional.empty();
    	LaboratoryEntity laboratoryEntity = checkUserAndGetLaboratoryEntity(laboratory.getCode());
    	if (laboratoryEntity != null) {
            String userCode = getUserCodeFromAuthentication();
            laboratory.setUserCode(userCode);
	        laboratoryJpaRepository.save(LaboratoryConverter.laboratoryModelToEntity(laboratory));
	        updatedLaboratory = getLaboratory(laboratoryEntity.getCode());
    	}
    	return updatedLaboratory;
    }
    
    @Override
    public boolean deleteLaboratory(String code) {
    	boolean deletedLaboratory = false;
    	LaboratoryEntity laboratoryEntity = checkUserAndGetLaboratoryEntity(code);
    	if (laboratoryEntity != null) {
    		laboratoryJpaRepository.delete(laboratoryEntity);
    		deletedLaboratory = true;
    	}
    	return deletedLaboratory;
    }

    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    
    private LaboratoryEntity checkUserAndGetLaboratoryEntity(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	return this.laboratoryJpaRepository.findByCodeAndUserCode(code, userCode);    	
    }
}
