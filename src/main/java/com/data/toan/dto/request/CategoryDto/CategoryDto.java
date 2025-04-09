package com.data.toan.dto.request.CategoryDto;

import com.data.toan.util.EnumRegex.TypeEnumCheck;
import com.data.toan.util.ListEnum.TypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryDto {

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;
    @TypeEnumCheck(regexp = "income|expense", name = "type", message = "Type mus be one in {income, expense}")
    private TypeEnum type;
    @NotNull(message = "Không được bỏ trống")
    private long user_id;
}
