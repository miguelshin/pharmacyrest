package com.pharmacy.rest.authentication.jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.rest.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
