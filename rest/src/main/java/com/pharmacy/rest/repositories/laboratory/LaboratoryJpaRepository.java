package com.pharmacy.rest.repositories.laboratory;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.LaboratoryEntity;

@Repository("laboratoryJpaRepository")
public interface LaboratoryJpaRepository extends JpaRepository<LaboratoryEntity, Serializable>{
    public abstract LaboratoryEntity findByCodeAndUserCode(String code, String userCode);
}
