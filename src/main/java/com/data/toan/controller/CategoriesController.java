package com.data.toan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.CategoryDto.CategoryDto;
import com.data.toan.dto.request.CategoryDto.CategoryEditDto;
import com.data.toan.services.CategoryService;
import com.data.toan.util.EnumRegex.TypeEnumCheck;
import com.data.toan.util.ListEnum.TypeEnum;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/Category")
@RestController
@Slf4j
@AllArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseData addCategory(@Valid @RequestBody CategoryDto category) {

        return categoryService.addCategory(category);
    }

    @PatchMapping()
    public ResponseData editCategory(@Valid @RequestBody CategoryEditDto category) {

        return categoryService.editCategory(category);

    }

    @DeleteMapping()
    public ResponseData deleteCategory(@RequestParam long id) {

        return categoryService.deleteCategory(id);
    }

    @GetMapping()
    public ResponseData getListCategory(@RequestParam(required = false) Long id) {

        return categoryService.getListCategory(id);
    }

    @GetMapping("/sortCategory")
    public ResponseData sortCategoriesByType(@RequestParam(required = false) Long id,
            @TypeEnumCheck(regexp = "income|expense", name = "type", message = "Type mus be one in {income, expense}") @RequestParam TypeEnum type) {

        return categoryService.sortCategoriesByType(type, id);
    }
}
