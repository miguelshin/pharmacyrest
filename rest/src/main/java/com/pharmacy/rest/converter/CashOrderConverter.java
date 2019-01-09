package com.pharmacy.rest.converter;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.CashOrderImageEntity;
import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderImage;
import com.pharmacy.rest.models.CashOrderProduct;

public class CashOrderConverter {

    public static CashOrder cashOrderEntityToModel(CashOrderEntity cashOrderEntity, List<CashOrderProductEntity> cashOrderProductsEntity, List<CashOrderImageEntity> cashOrderImagesEntity) {
        CashOrder cashOrder = new CashOrder();
        cashOrder.setCode(cashOrderEntity.getCode());
        cashOrder.setDate(cashOrderEntity.getDate());
        cashOrder.setObservations(cashOrderEntity.getObservations());
        cashOrder.setPharmacy(PharmacyConverter.pharmacyEntityToModel(cashOrderEntity.getPharmacy()));
        cashOrder.setCashOrderProducts(new ArrayList<>());
        for (CashOrderProductEntity cashOrderProductEntity : cashOrderProductsEntity) {
        	CashOrderProduct cashOrderProduct = CashOrderProductConverter.cashOrderProductEntityToModel(cashOrderProductEntity);
        	cashOrder.getCashOrderProducts().add(cashOrderProduct);
        }

        cashOrder.setCashOrderImages(new ArrayList<>());
        for (CashOrderImageEntity cashOrderImageEntity : cashOrderImagesEntity) {
            CashOrderImage cashOrderImage = CashOrderImageConverter.cashOrderImageEntityToModel(cashOrderImageEntity);
            cashOrder.getCashOrderImages().add(cashOrderImage);
        }

        return cashOrder;
    }

    public static CashOrderEntity cashOrderModelToEntity(CashOrder cashOrder) {
        CashOrderEntity cashOrderEntity = new CashOrderEntity();
        cashOrderEntity.setCode(cashOrder.getCode());
        cashOrderEntity.setDate(cashOrder.getDate());
        cashOrderEntity.setObservations(cashOrder.getObservations());
        cashOrderEntity.setPharmacy(PharmacyConverter.pharmacyModelToEntity(cashOrder.getPharmacy()));
        return cashOrderEntity;
    }

}
