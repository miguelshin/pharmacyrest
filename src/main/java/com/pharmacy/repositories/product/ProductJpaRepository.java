package com.pharmacy.repositories.product;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entities.ProductEntity;;

@Repository("productJpaRepository")
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Serializable>{
    public abstract ProductEntity findByCode(String code);
    public abstract List<ProductEntity> findByLaboratoryCode(String laboratoryCode);
}
