package com.pharmacy.rest.authentication.jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.common.authentication.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
