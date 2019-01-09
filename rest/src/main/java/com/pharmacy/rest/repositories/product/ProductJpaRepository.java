package com.pharmacy.rest.repositories.product;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.ProductEntity;;

@Repository("productJpaRepository")
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Serializable>{
    ProductEntity findByCodeAndLaboratoryUserCode(String code, String userCode);
    List<ProductEntity> findByLaboratoryCodeAndLaboratoryUserCode(String laboratoryCode, String userCode);
}
