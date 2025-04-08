package com.data.toan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.toan.model.CategoriesEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoriesEntity, Long> {

}
