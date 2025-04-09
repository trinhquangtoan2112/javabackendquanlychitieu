package com.data.toan.dto.request.CategoryDto;

import com.data.toan.util.EnumRegex.TypeEnumCheck;
import com.data.toan.util.ListEnum.TypeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryEditDto {
    @NotNull(message = "Không được bỏ trống")
    private long id;
    private String name;
    @TypeEnumCheck(regexp = "income|expense", name = "type", message = "Type mus be one in {income, expense}")
    private TypeEnum type;

}
