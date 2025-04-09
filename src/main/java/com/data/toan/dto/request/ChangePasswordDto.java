package com.data.toan.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChangePasswordDto {
    @NotBlank(message = "ID khoong được để trống")
    @NotNull
    private long id;
    @NotBlank(message = "Password không được để trống")
    @NotNull
    private String password;
}
