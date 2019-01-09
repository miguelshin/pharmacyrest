package com.pharmacy.rest.repositories.CashOrderImage;

import com.pharmacy.rest.entities.CashOrderImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("cashOrderImageJpaRepository")
public interface CashOrderImageJpaRepository extends JpaRepository<CashOrderImageEntity, Serializable>{
    List<CashOrderImageEntity> findByIdCashOrderCode(String cashOrderCode);
    void deleteByIdCashOrderCode(String cashOrderCode);
}
