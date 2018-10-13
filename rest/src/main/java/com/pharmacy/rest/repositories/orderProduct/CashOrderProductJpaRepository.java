package com.pharmacy.rest.repositories.orderProduct;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.CashOrderProductEntity;
import com.pharmacy.rest.entities.CashOrderProductPKEntity;

@Repository("cashOrderProductJpaRepository")
public interface CashOrderProductJpaRepository extends JpaRepository<CashOrderProductEntity, Serializable>{
    public abstract CashOrderProductEntity findById(CashOrderProductPKEntity code);
}
