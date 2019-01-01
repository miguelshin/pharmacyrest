package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.entities.CashOrderProductPKEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderProduct;

public class CashOrderProductConverter {

    public static CashOrderProduct cashOrderProductEntityToModel(CashOrderProductEntity cashOrderProductEntity) {
        CashOrderProduct cashOrderProduct = new CashOrderProduct();
        cashOrderProduct.setAmount(cashOrderProductEntity.getAmount());
        cashOrderProduct.setProduct(ProductConverter.productEntityToModel(cashOrderProductEntity.getId().getProduct()));
        cashOrderProduct.setQuantity(cashOrderProductEntity.getQuantity());
        cashOrderProduct.setImageUrl(cashOrderProductEntity.getImageUrl());
        return cashOrderProduct;
    }

    public static CashOrderProductEntity cashOrderProductModelToEntity(CashOrderProduct cashOrderProduct, CashOrder cashOrder) {
        CashOrderProductEntity cashOrderProductEntity = new CashOrderProductEntity();
        cashOrderProductEntity.setAmount(cashOrderProduct.getAmount());
        cashOrderProductEntity.setId(new CashOrderProductPKEntity());        
        cashOrderProductEntity.getId().setProduct(ProductConverter.productModelToEntity(cashOrderProduct.getProduct()));
        cashOrderProductEntity.getId().setCashOrder(CashOrderConverter.cashOrderModelToEntity(cashOrder));
        cashOrderProductEntity.setImageUrl(cashOrderProduct.getImageUrl());
        return cashOrderProductEntity;
    }

}
