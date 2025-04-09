package com.data.toan.dto.request.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class TransactionEditDTO {
    private long id;
    private long categoryId;
    private Long amount;
    private String note;

}
