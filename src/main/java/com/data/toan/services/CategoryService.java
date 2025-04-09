package com.data.toan.services;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.CategoryDto.CategoryDto;
import com.data.toan.dto.request.CategoryDto.CategoryEditDto;
import com.data.toan.util.ListEnum.TypeEnum;

public interface CategoryService {
    ResponseData addCategory(CategoryDto category);

    ResponseData editCategory(CategoryEditDto category);

    ResponseData deleteCategory(long id);

    ResponseData getListCategory(Long id);

    ResponseData sortCategoriesByType(TypeEnum type, Long id);

}
