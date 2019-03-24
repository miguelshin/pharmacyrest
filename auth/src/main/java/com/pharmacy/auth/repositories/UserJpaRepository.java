package com.pharmacy.auth.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.auth.entities.UserEntity;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<UserEntity, Serializable>{
    UserEntity findByUsername(String username);
}