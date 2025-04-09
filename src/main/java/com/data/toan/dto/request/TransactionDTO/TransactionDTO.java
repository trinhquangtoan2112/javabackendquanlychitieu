package com.data.toan.dto.request.TransactionDTO;

import lombok.Getter;

@Getter
public class TransactionDTO {

    private long userId;
    private long categoryId;
    private long amount;
    private String note;

}
