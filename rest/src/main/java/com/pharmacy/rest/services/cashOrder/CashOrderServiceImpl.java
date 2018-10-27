package com.pharmacy.rest.services.cashOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.CashOrderConverter;
import com.pharmacy.rest.converter.CashOrderProductConverter;
import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.CashOrderProductEntity;
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
    @Qualifier("laboratoryQueryDSLRepository")
    private LaboratoryQueryDSLRepository laboratoryQueryDSLRepository;

    @Autowired
    @Qualifier("pharmacyJpaRepository")
    private PharmacyJpaRepository pharmacyJpaRepository;

    @Autowired
    @Qualifier("productJpaRepository")
    private ProductJpaRepository productJpaRepository;

    @Override
    public List<CashOrder> searchCashOrders(Date date) {
        List<CashOrder> cashOrders = new ArrayList<CashOrder>();
    	List<CashOrderEntity> cashOrderEntities = checkUserAndGetCashOrderEntities(date);
        for (CashOrderEntity cashOrderEntity : cashOrderEntities) {
        	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrderCode(cashOrderEntity.getCode());
            cashOrders.add(CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities));
        }
        return cashOrders;
    }

    @Override
    public Optional<CashOrder> getCashOrder(String code) {
		CashOrderEntity cashOrderEntity = checkUserAndGetCashOrderEntity(code);
    	List<CashOrderProductEntity> cashOrderProductEntities = cashOrderProductJpaRepository.findByIdCashOrderCode(cashOrderEntity.getCode());
    	CashOrder cashOrder = CashOrderConverter.cashOrderEntityToModel(cashOrderEntity, cashOrderProductEntities);
        return Optional.of(cashOrder);
    }
    
    @Override
    public CashOrder saveCashOrder(CashOrder cashOrder) {
    	if (checkUserIfAccessPharmacyIsGranted(cashOrder.getPharmacy().getCode())) {
    		CashOrderEntity cashOrderEntity = CashOrderConverter.cashOrderModelToEntity(cashOrder);
    		cashOrderJpaRepository.save(cashOrderEntity);
    		for (CashOrderProduct cashOrderProduct : cashOrder.getCashOrderProducts()) {
    			if (checkUserIfAccessLaboratoryIsGranted(cashOrderProduct.getProduct().getCode())) {
    				cashOrderProductJpaRepository.save(CashOrderProductConverter.cashOrderProductModelToEntity(cashOrderProduct, cashOrder));
    			}
    		}
    		return getCashOrder(cashOrder.getCode()).get();
    	}
    	return null;
    }
    
    @Override
    public Optional<CashOrder> updateCashOrder(CashOrder cashOrder) {
    	Optional<CashOrder> updatedCashOrder = Optional.empty();
		CashOrderEntity cashOrderEntity = checkUserAndGetCashOrderEntity(cashOrder.getCode());
    	if (cashOrderEntity != null) {
    		cashOrderEntity = CashOrderConverter.cashOrderModelToEntity(cashOrder);
    		cashOrderJpaRepository.save(cashOrderEntity);
    		cashOrderProductJpaRepository.deleteByIdCashOrderCode(cashOrderEntity.getCode());
    		for (CashOrderProduct cashOrderProduct : cashOrder.getCashOrderProducts()) {
    			if (checkUserIfAccessLaboratoryIsGranted(cashOrderProduct.getProduct().getCode())) {
    				cashOrderProductJpaRepository.save(CashOrderProductConverter.cashOrderProductModelToEntity(cashOrderProduct, cashOrder));
    			}
    		}
    		updatedCashOrder = getCashOrder(cashOrder.getCode());
    	}
    	return updatedCashOrder;
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
    
    private boolean checkUserIfAccessPharmacyIsGranted(String pharmacyCode) {
    	String userCode = getUserCodeFromAuthentication();
    	return (pharmacyJpaRepository.findByCodeAndUserCode(pharmacyCode, userCode) != null);
    }
    
    private boolean checkUserIfAccessLaboratoryIsGranted(String laboratoryCode) {
    	String userCode = getUserCodeFromAuthentication();
    	return (productJpaRepository.findByCodeAndLaboratoryUserCode(laboratoryCode, userCode) != null);
    }

    private List<CashOrderEntity> checkUserAndGetCashOrderEntities(Date date) {
		String userCode = getUserCodeFromAuthentication();
 		return cashOrderJpaRepository.findByDateAndPharmacyUserCode(date, userCode);
    }
    
    private CashOrderEntity checkUserAndGetCashOrderEntity(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	return cashOrderJpaRepository.findByCodeAndPharmacyUserCode(code, userCode);
    }

    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    
}
