package com.pharmacy.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.pharmacy.entities.PharmacyEntity;
import com.pharmacy.entities.QPharmacyEntity;

@Repository("pharmacyQueryDSLRepository")
public class PharmacyQueryDSLRepository {
    private QPharmacyEntity qc = QPharmacyEntity.pharmacyEntity;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<PharmacyEntity> searchPharmacies(String textName) {
        List<PharmacyEntity> pharmacies = null;
        JPAQuery<PharmacyEntity> query = new JPAQuery<PharmacyEntity>(em);
        BooleanBuilder predicateBuilder = null;
        if (textName != null && !textName.isEmpty()) {
            predicateBuilder = new BooleanBuilder(qc.name.contains(textName));
            pharmacies = query.select(qc).from(qc).where(predicateBuilder).fetch();
        } else {
            pharmacies = query.select(qc).from(qc).fetch();
        }

        return pharmacies;
    }
}
