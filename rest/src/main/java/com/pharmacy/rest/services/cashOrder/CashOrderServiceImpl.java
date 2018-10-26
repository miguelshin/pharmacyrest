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
import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderProduct;
import com.pharmacy.rest.repositories.cashOrder.CashOrderJpaRepository;
import com.pharmacy.rest.repositories.cashOrderProduct.CashOrderProductJpaRepository;
import com.pharmacy.rest.repositories.laboratory.LaboratoryQueryDSLRepository;
import com.pharmacy.rest.repositories.pharmacy.PharmacyJpaRepository;
import com.pharmacy.rest.repositories.product.ProductJpaRepository;

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

    @Autowired
    @Qualifier("pharmacyJpaRepository")
    private PharmacyJpaRepository pharmacyJpaRepository;

    @Autowired
    @Qualifier("productJpaRepository")
    private ProductJpaRepository productJpaRepository;

    @Override
    public List<CashOrder> searchCashOrders(Date date) {
    	String userCode = getUserCodeFromAuthentication();
        List<CashOrderEntity> cashOrderEntities = cashOrderJpaRepository.findByDateAndPharmacyUserCode(date, userCode);
        List<CashOrder> cashOrders = new ArrayList<CashOrder>();
        for (CashOrderEntity cashOrderEntity : cashOrderEntities) {
        	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrderCode(cashOrderEntity.getCode());
            cashOrders.add(CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities));
        }
        return cashOrders;
    }

    @Override
    public CashOrder getCashOrder(String code) {
    	String userCode = getUserCodeFromAuthentication();
        CashOrderEntity cashOrderEntity = cashOrderJpaRepository.findByCodeAndPharmacyUserCode(code, userCode);
    	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrderCode(cashOrderEntity.getCode());
    	CashOrder cashOrder = CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities);
        return cashOrder;
    }
    
    @Override
    public CashOrder saveCashOrder(CashOrder cashOrder) {
    	String userCode = getUserCodeFromAuthentication();
    	PharmacyEntity pharmacyEntity = pharmacyJpaRepository.findByCodeAndUserCode(cashOrder.getPharmacy().getCode(), userCode);
    	if (pharmacyEntity != null) {
    		CashOrderEntity cashOrderEntity = CashOrderConverter.cashOrderModelToEntity(cashOrder);
    		cashOrderJpaRepository.save(cashOrderEntity);
    		for (CashOrderProduct cashOrderProduct : cashOrder.getCashOrderProducts()) {
    			if (productAllowsToUser(cashOrderProduct.getProduct().getCode(), userCode)) {
    				cashOrderProductJpaRepository.save(CashOrderProductConverter.cashOrderProductModelToEntity(cashOrderProduct, cashOrder));
    			}
    		}
    		return getCashOrder(cashOrder.getCode());
    	}
    	return null;
    }
    
    @Override
    public CashOrder updateCashOrder(CashOrder cashOrder) {
    	String userCode = getUserCodeFromAuthentication();
		CashOrderEntity cashOrderEntity = cashOrderJpaRepository.findByCodeAndPharmacyUserCode(cashOrder.getCode(), userCode);
    	if (cashOrderEntity != null) {
    		cashOrderEntity = CashOrderConverter.cashOrderModelToEntity(cashOrder);
    		cashOrderJpaRepository.save(cashOrderEntity);
    		for (CashOrderProduct cashOrderProduct : cashOrder.getCashOrderProducts()) {
    			if (productAllowsToUser(cashOrderProduct.getProduct().getCode(), userCode)) {
    				cashOrderProductJpaRepository.save(CashOrderProductConverter.cashOrderProductModelToEntity(cashOrderProduct, cashOrder));
    			}
    		}
    		cashOrder = getCashOrder(cashOrder.getCode());
    	}
    	return null;
    }
    
    @Override
    public boolean deleteCashOrder(String code) {
    	String userCode = getUserCodeFromAuthentication();
		CashOrderEntity cashOrderEntity = cashOrderJpaRepository.findByCodeAndPharmacyUserCode(code, userCode);
    	if (cashOrderEntity != null) {
    		List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrderCode(cashOrderEntity.getCode());
    		for (CashOrderProductEntity cashOrderProductEntity : cashOrderProductEntities) {
    			cashOrderProductJpaRepository.delete(cashOrderProductEntity);
    		}
    		cashOrderJpaRepository.delete(cashOrderEntity);
    		return true;
    	}
    	return false;
    }

    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    
    private boolean productAllowsToUser(String productCode, String userCode) {
		return productJpaRepository.findByCodeAndLaboratoryUserCode(productCode, userCode) != null;   	
    }

}
