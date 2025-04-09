package com.data.toan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.toan.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String mail);

    boolean existsById(long id);

    Optional<UserEntity> findById(long id);
}