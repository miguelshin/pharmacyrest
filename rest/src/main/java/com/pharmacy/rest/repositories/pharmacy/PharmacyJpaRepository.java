package com.pharmacy.rest.repositories.pharmacy;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.PharmacyEntity;;

@Repository("pharmacyJpaRepository")
public interface PharmacyJpaRepository extends JpaRepository<PharmacyEntity, Serializable>{
    PharmacyEntity findByCodeAndUserCode(String code, String userCode);
}
