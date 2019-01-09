package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.CashOrderImageEntity;
import com.pharmacy.rest.entities.CashOrderImagePKEntity;
import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.CashOrderImage;

public class CashOrderImageConverter {

    public static CashOrderImage cashOrderImageEntityToModel(CashOrderImageEntity cashOrderImageEntity) {
        CashOrderImage cashOrderImage = new CashOrderImage();
        cashOrderImage.setUrl(cashOrderImageEntity.getId().getUrl());
        return cashOrderImage;
    }

    public static CashOrderImageEntity cashOrderImageModelToEntity(CashOrderImage cashOrderImage, CashOrder cashOrder) {
        CashOrderImageEntity cashOrderImageEntity = new CashOrderImageEntity();
        cashOrderImageEntity.setId(new CashOrderImagePKEntity());
        cashOrderImageEntity.getId().setUrl(cashOrderImage.getUrl());
        cashOrderImageEntity.getId().setCashOrder(CashOrderConverter.cashOrderModelToEntity(cashOrder));
        return cashOrderImageEntity;
    }

}
