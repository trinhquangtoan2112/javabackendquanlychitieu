package com.data.toan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.toan.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}