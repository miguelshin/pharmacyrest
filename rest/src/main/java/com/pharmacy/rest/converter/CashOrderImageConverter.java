package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.CashOrderImageEntity;
import com.pharmacy.rest.models.CashOrderImage;

public class CashOrderImageConverter {

    public static CashOrderImage cashOrderImageEntityToModel(CashOrderImageEntity cashOrderImageEntity) {
        CashOrderImage cashOrderImage = new CashOrderImage();
        cashOrderImage.setUrl(cashOrderImageEntity.getUrl());
        return cashOrderImage;
    }

    public static CashOrderImageEntity cashOrderImageModelToEntity(CashOrderImage cashOrderImage) {
        CashOrderImageEntity cashOrderImageEntity = new CashOrderImageEntity();
        cashOrderImageEntity.setUrl(cashOrderImage.getUrl());
        return cashOrderImageEntity;
    }

}
