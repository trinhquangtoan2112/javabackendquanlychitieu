package com.data.toan.services;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.TransactionDTO.TransactionDTO;
import com.data.toan.dto.request.TransactionDTO.TransactionEditDTO;

public interface TransactionService {

    ResponseData addTransaction(TransactionDTO transactionDTO);

    ResponseData editTransaction(TransactionEditDTO transactionEditDTO);

    ResponseData deleteTransaction(long id);

    ResponseData getTransaction(long id);
}