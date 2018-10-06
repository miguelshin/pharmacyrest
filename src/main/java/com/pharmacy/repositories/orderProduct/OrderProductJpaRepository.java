package com.pharmacy.repositories.orderProduct;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entities.OrderEntity;
import com.pharmacy.entities.OrderProductEntity;
import com.pharmacy.entities.OrderProductPKEntity;
import com.pharmacy.entities.PharmacyEntity;;

@Repository("orderProductJpaRepository")
public interface OrderProductJpaRepository extends JpaRepository<OrderProductEntity, Serializable>{
    public abstract OrderProductEntity findById(OrderProductPKEntity code);
}
