package com.data.toan.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class ResponseData<T> {
    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL) // khi data khac null thi hien
    private T data;

    public ResponseData(int status, String mess) {
        this.status = status;
        this.message = mess;
    }
}
