package com.pharmacy.repositories.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entities.OrderEntity;
import com.pharmacy.entities.PharmacyEntity;;

@Repository("orderJpaRepository")
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Serializable>{
    public abstract OrderEntity findByCode(String code);
    public abstract List<OrderEntity> findByPharmacyCodeAndDate(String pharmacyCode, Date date);
}
