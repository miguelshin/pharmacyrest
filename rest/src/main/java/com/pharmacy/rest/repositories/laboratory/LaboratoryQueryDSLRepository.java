package com.pharmacy.rest.repositories.laboratory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.pharmacy.rest.entities.LaboratoryEntity;
import com.pharmacy.rest.entities.QLaboratoryEntity;

@Repository("laboratoryQueryDSLRepository")
public class LaboratoryQueryDSLRepository {
    private QLaboratoryEntity qc = QLaboratoryEntity.laboratoryEntity;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<LaboratoryEntity> searchLaboratories(String textName, String userCode) {
        List<LaboratoryEntity> laboratories = null;
        JPAQuery<LaboratoryEntity> query = new JPAQuery<LaboratoryEntity>(em);
        BooleanBuilder predicateBuilder = null;
        if (textName != null && !textName.isEmpty()) {
            predicateBuilder = new BooleanBuilder(qc.name.containsIgnoreCase(textName)).and(qc.userCode.equalsIgnoreCase(userCode));
            laboratories = query.select(qc).from(qc).where(predicateBuilder).fetch();
        } else {
            laboratories = query.select(qc).from(qc).fetch();
        }

        return laboratories;
    }
}
