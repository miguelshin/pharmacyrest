package com.pharmacy.rest.repositories.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.PharmacyEntity;;

@Repository("orderJpaRepository")
public interface OrderJpaRepository extends JpaRepository<CashOrderEntity, Serializable>{
    public abstract CashOrderEntity findByCode(String code);
    public abstract List<CashOrderEntity> findByPharmacyCodeAndDate(String pharmacyCode, Date date);
}
