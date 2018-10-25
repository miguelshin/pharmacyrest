package com.pharmacy.rest.converter;

import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.entities.ProductEntity;
import com.pharmacy.rest.models.Laboratory;
import com.pharmacy.rest.models.Product;

public class ProductConverter {

    public static Product productEntityToModel(ProductEntity pharmacyEntity) {
        Product product = new Product();
        product.setCode(pharmacyEntity.getCode());
        product.setName(pharmacyEntity.getName());
        Laboratory laboratory = new Laboratory();
        laboratory.setCode(pharmacyEntity.getCode());
        laboratory.setName(pharmacyEntity.getName());
        product.setLaboratory(laboratory);
        return product;
    }

    public static ProductEntity productModelToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode(product.getCode());
        productEntity.setName(product.getName());
        LaboratoryEntity laboratoryEntity = new LaboratoryEntity();
        laboratoryEntity.setCode(product.getLaboratory().getCode());
        laboratoryEntity.setName(product.getLaboratory().getName());
        productEntity.setLaboratory(laboratoryEntity);
        return productEntity;
    }

}
