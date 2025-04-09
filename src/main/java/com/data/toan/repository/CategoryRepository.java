package com.data.toan.repository;

import java.util.List;
import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.toan.dto.reponse.Category.CategoryResponseDTO;
import com.data.toan.model.CategoriesEntity;
import com.data.toan.util.ListEnum.TypeEnum;

@Repository
public interface CategoryRepository extends JpaRepository<CategoriesEntity, Long> {
    Optional<CategoriesEntity> findById(Long id);

    List<CategoriesEntity> findByUserIsNull();

    List<CategoriesEntity> findByUserId(Long id);

    List<CategoriesEntity> findByTypeAndUserIsNull(TypeEnum type);

    List<CategoriesEntity> findByTypeAndUser_Id(TypeEnum type, Long id);
}
