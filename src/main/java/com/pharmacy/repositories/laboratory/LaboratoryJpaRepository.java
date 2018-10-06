package com.pharmacy.repositories.laboratory;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entities.LaboratoryEntity;
import com.pharmacy.entities.PharmacyEntity;;

@Repository("laboratoryJpaRepository")
public interface LaboratoryJpaRepository extends JpaRepository<LaboratoryEntity, Serializable>{
    public abstract LaboratoryEntity findByCode(String code);
}
