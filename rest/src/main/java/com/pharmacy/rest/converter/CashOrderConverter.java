package com.pharmacy.rest.converter;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderProduct;

public class CashOrderConverter {

    public static CashOrder cashOrderEntityToModel(CashOrderEntity cashOrderEntity, List<CashOrderProductEntity> cashOrderProductsEntity) {
        CashOrder cashOrder = new CashOrder();
        cashOrder.setCode(cashOrderEntity.getCode());
        cashOrder.setDate(cashOrderEntity.getDate());
        cashOrder.setPharmacy(PharmacyConverter.pharmacyEntityToModel(cashOrderEntity.getPharmacy()));
        cashOrder.setCashOrderProducts(new ArrayList<CashOrderProduct>());
        for (CashOrderProductEntity cashOrderProductEntity : cashOrderProductsEntity) {
        	CashOrderProduct cashOrderProduct = CashOrderProductConverter.cashOrderProductEntityToModel(cashOrderProductEntity);
        	cashOrder.getCashOrderProducts().add(cashOrderProduct);
        }
        return cashOrder;
    }

    public static CashOrderEntity cashOrderModelToEntity(CashOrder cashOrder) {
        CashOrderEntity cashOrderEntity = new CashOrderEntity();
        cashOrderEntity.setCode(cashOrder.getCode());
        cashOrderEntity.setDate(cashOrder.getDate());
        cashOrderEntity.setPharmacy(PharmacyConverter.pharmacyModelToEntity(cashOrder.getPharmacy()));
        return cashOrderEntity;
    }

}
