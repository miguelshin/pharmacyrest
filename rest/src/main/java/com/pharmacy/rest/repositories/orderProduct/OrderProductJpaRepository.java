package com.pharmacy.rest.repositories.orderProduct;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.rest.entities.OrderEntity;
import com.pharmacy.rest.entities.OrderProductEntity;
import com.pharmacy.rest.entities.OrderProductPKEntity;
import com.pharmacy.rest.entities.PharmacyEntity;;

@Repository("orderProductJpaRepository")
public interface OrderProductJpaRepository extends JpaRepository<OrderProductEntity, Serializable>{
    public abstract OrderProductEntity findById(OrderProductPKEntity code);
}
