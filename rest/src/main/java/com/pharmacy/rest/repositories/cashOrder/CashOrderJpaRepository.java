package com.pharmacy.rest.repositories.cashOrder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.CashOrderEntity;
import com.pharmacy.rest.entities.PharmacyEntity;;

@Repository("orderJpaRepository")
public interface CashOrderJpaRepository extends JpaRepository<CashOrderEntity, Serializable>{
    public abstract List<CashOrderEntity> findByDateAndPharmacyUserCode(Date date, String userCode);
    public abstract CashOrderEntity findByCodeAndPharmacyUserCode(String code, String userCode);
    public abstract CashOrderEntity findByCode(String code);
    public abstract List<CashOrderEntity> findByPharmacyCodeAndDate(String pharmacyCode, Date date);
    public abstract CashOrderEntity findByPharmacy(PharmacyEntity pharmacy);

}
