package com.pharmacy.rest.services.cashOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.CashOrderConverter;
import com.pharmacy.rest.converter.CashOrderProductConverter;
import com.pharmacy.rest.converter.LaboratoryConverter;
import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderProduct;
import com.pharmacy.rest.models.Laboratory;
import com.pharmacy.rest.repositories.cashOrder.CashOrderJpaRepository;
import com.pharmacy.rest.repositories.cashOrderProduct.CashOrderProductJpaRepository;
import com.pharmacy.rest.repositories.laboratory.LaboratoryJpaRepository;
import com.pharmacy.rest.repositories.laboratory.LaboratoryQueryDSLRepository;
import com.pharmacy.rest.repositories.pharmacy.PharmacyQueryDSLRepository;

@Service("cashOrderService")
public class CashOrderServiceImpl implements CashOrderService {
    @Autowired
    @Qualifier("cashOrderJpaRepository")
    private CashOrderJpaRepository cashOrderJpaRepository;

    @Autowired
    @Qualifier("cashOrderProductJpaRepository")
    private CashOrderProductJpaRepository cashOrderProductJpaRepository;

    @Autowired
    @Qualifier("cashOrderQueryDSLRepository")
    private LaboratoryQueryDSLRepository cashOrderQueryDSLRepository;

    @Override
    public List<CashOrder> searchCashOrders(Date date) {
    	String userCode = getUserCodeFromAuthentication();
        List<CashOrderEntity> cashOrderEntities = cashOrderJpaRepository.findByDateAndPharmacyUserCode(date, userCode);
        List<CashOrder> cashOrders = new ArrayList<CashOrder>();
        for (CashOrderEntity cashOrderEntity : cashOrderEntities) {
        	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrder(cashOrderEntity.getCode());
            cashOrders.add(CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities));
        }
        return cashOrders;
    }

    @Override
    public CashOrder getCashOrder(String code) {
    	String userCode = getUserCodeFromAuthentication();
        CashOrderEntity cashOrderEntity = cashOrderJpaRepository.findByCodeAndPharmacyUserCode(code, userCode);
    	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrder(cashOrderEntity.getCode());
    	CashOrder cashOrder = CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities);
        return cashOrder;
    }
    
    // TODO: los siguientes métodos
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
