package com.data.toan.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.data.toan.dto.reponse.ResponeError;
import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.reponse.Category.CategoryResponseDTO;
import com.data.toan.dto.request.CategoryDto.CategoryDto;
import com.data.toan.dto.request.CategoryDto.CategoryEditDto;
import com.data.toan.exception.Category.CategoryDontExits;
import com.data.toan.exception.User.UserDontExits;
import com.data.toan.model.CategoriesEntity;
import com.data.toan.model.UserEntity;
import com.data.toan.repository.CategoryRepository;
import com.data.toan.repository.UserRepository;
import com.data.toan.services.CategoryService;
import com.data.toan.util.ListEnum.TypeEnum;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseData addCategory(CategoryDto category) {
        UserEntity user = userRepository.findById(category.getUser_id())
                .orElseThrow(() -> new UserDontExits("User not found"));
        CategoriesEntity categoriesEntity = CategoriesEntity.builder().name(category.getName()).type(category.getType())
                .user(user)
                .build();

        categoryRepository.save(categoriesEntity);
        return new ResponseData(HttpStatus.CREATED.value(), "Thêm giá trị thành công", category);
    }

    ///////////////
    @Override
    public ResponseData editCategory(CategoryEditDto category) {
        CategoriesEntity categoriesEntity = categoryRepository.findById(category.getId()).orElseThrow(() -> {
            throw new CategoryDontExits("Category không tồn tại");
        });
        if (category.getType() != null) {
            categoriesEntity.setType(category.getType());
        }
        if (category.getName() != null) {
            categoriesEntity.setName(category.getName());
        }
        try {
            categoryRepository.save(categoriesEntity);
            return new ResponseData(HttpStatus.NO_CONTENT.value(), "Cập nhập thành công");
        } catch (Exception e) {
            return new ResponeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cập nhập không thành công");
        }

    }

    @Override
    public ResponseData deleteCategory(long id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseData(HttpStatus.OK.value(), "Xóa thành công");
        } catch (Exception e) {
            return new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Xóa không thành công");
        }

    }

    @Override
    public ResponseData getListCategory(Long id) {
        List<CategoriesEntity> listCategory = categoryRepository.findByUserIsNull();
        List<CategoryResponseDTO> resultList = listCategory.stream()
                .map(category -> CategoryResponseDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .type(category.getType().toString())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));
        ;
        if (id != null) {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new UserDontExits("User not found"));
            List<CategoriesEntity> listCategoryById = categoryRepository.findByUserId(id);
            for (CategoriesEntity s : listCategoryById) {
                resultList.add(CategoryResponseDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .type(s.getType().toString())
                        .build());
            }
        }
        return new ResponseData<>(HttpStatus.OK.value(), "Danh sách", resultList);
    }

    @Override
    public ResponseData sortCategoriesByType(TypeEnum type, Long id) {

        List<CategoriesEntity> result = categoryRepository.findByTypeAndUserIsNull(type);

        List<CategoryResponseDTO> resultList = result.stream()
                .map(category -> CategoryResponseDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .type(category.getType().toString())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));
        ;
        if (id != null) {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new UserDontExits("User not found"));
            List<CategoriesEntity> listCategoryById = categoryRepository.findByTypeAndUser_Id(type, id);
            for (CategoriesEntity s : listCategoryById) {
                resultList.add(CategoryResponseDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .type(s.getType().toString())
                        .build());
            }
        }
        return new ResponseData<>(HttpStatus.OK.value(), "Danh sách", resultList);
    }

}
