package com.pharmacy.rest.repositories.cashOrderProduct;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.CashOrderProductEntity;

@Repository("cashOrderProductJpaRepository")
public interface CashOrderProductJpaRepository extends JpaRepository<CashOrderProductEntity, Serializable>{
    public abstract List<CashOrderProductEntity> findByIdCashOrderCode(String cashOrderCode);
}
