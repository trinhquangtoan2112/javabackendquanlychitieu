package com.data.toan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.toan.model.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}