package com.data.toan.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class RegisterDto implements Serializable {
    @NotBlank(message = "Email không được để trống")
    @NotNull
    private String mail;
    @NotBlank(message = "Passworld không được để trống")
    @NotNull
    private String password;
}
