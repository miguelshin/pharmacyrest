package com.pharmacy.rest.repositories.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.pharmacy.rest.entities.ProductEntity;
import com.pharmacy.rest.entities.QProductEntity;

@Repository("productQueryDSLRepository")
public class ProductQueryDSLRepository {
    private QProductEntity qc = QProductEntity.productEntity;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<ProductEntity> searchProducts(String textName, String userCode) {
        List<ProductEntity> products = null;
        JPAQuery<ProductEntity> query = new JPAQuery<ProductEntity>(em);
        BooleanBuilder predicateBuilder = null;
        if (textName != null && !textName.isEmpty()) {
            predicateBuilder = new BooleanBuilder(qc.name.containsIgnoreCase(textName))
            		.and(qc.laboratory.userCode.eq(userCode));
            products = query.select(qc).from(qc).where(predicateBuilder).fetch();
        } else {
            products = query.select(qc).from(qc).fetch();
        }

        return products;
    }

    public List<ProductEntity> searchProductsByLaboratoryCode(String textName, String laboratoryCode, String userCode) {
        List<ProductEntity> products = null;
        JPAQuery<ProductEntity> query = new JPAQuery<ProductEntity>(em);
        BooleanBuilder predicateBuilder = null;
        if (textName != null && !textName.isEmpty()) {
            predicateBuilder = new BooleanBuilder(qc.name.containsIgnoreCase(textName))
            		.and(qc.laboratory.code.eq(laboratoryCode))
            		.and(qc.laboratory.userCode.eq(userCode));
            products = query.select(qc).from(qc).where(predicateBuilder).fetch();
        } else {
            products = query.select(qc).from(qc).fetch();
        }

        return products;
    }
}
