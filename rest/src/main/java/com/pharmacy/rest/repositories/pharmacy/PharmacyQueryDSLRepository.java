package com.pharmacy.rest.repositories.pharmacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.entities.QPharmacyEntity;

@Repository("pharmacyQueryDSLRepository")
public class PharmacyQueryDSLRepository {
    private QPharmacyEntity qc = QPharmacyEntity.pharmacyEntity;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<PharmacyEntity> searchPharmacies(String textName, String userCode) {
        List<PharmacyEntity> pharmacies = null;
        JPAQuery<PharmacyEntity> query = new JPAQuery<PharmacyEntity>(em);
        BooleanBuilder predicateBuilder = new BooleanBuilder(qc.userCode.equalsIgnoreCase(userCode));
        if (textName != null && !textName.isEmpty()) {
             predicateBuilder = predicateBuilder.and(qc.name.containsIgnoreCase(textName));
        }
        pharmacies = query.select(qc).from(qc).where(predicateBuilder).fetch();
        
        return pharmacies;
    }
}
