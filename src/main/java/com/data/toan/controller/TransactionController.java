package com.data.toan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.TransactionDTO.TransactionDTO;
import com.data.toan.dto.request.TransactionDTO.TransactionEditDTO;
import com.data.toan.services.TransactionService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.crypto.dsig.TransformService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/Transaction")
@RestController
@Slf4j
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping()
    public ResponseData getTransaction(@RequestParam Long id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping()
    public ResponseData postTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.addTransaction(transactionDTO);
    }

    @DeleteMapping()
    public ResponseData deleteTransaction(@RequestParam long id) {
        return transactionService.deleteTransaction(id);
    }

    @PatchMapping()
    public ResponseData updateTransaction(@RequestBody TransactionEditDTO transactionEditDTO) {
        return transactionService.editTransaction(transactionEditDTO);
    }
}
