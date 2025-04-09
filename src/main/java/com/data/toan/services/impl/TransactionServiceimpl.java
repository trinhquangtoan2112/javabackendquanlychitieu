package com.data.toan.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.TransactionDTO.TransactionDTO;
import com.data.toan.dto.request.TransactionDTO.TransactionEditDTO;
import com.data.toan.exception.Category.CategoryDontExits;
import com.data.toan.exception.User.UserDontExits;
import com.data.toan.model.CategoriesEntity;
import com.data.toan.model.TransactionEntity;
import com.data.toan.model.UserEntity;
import com.data.toan.repository.CategoryRepository;
import com.data.toan.repository.TransactionRepository;
import com.data.toan.repository.UserRepository;
import com.data.toan.services.TransactionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceimpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseData addTransaction(TransactionDTO transactionDTO) {
        UserEntity user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new UserDontExits("User not found"));
        CategoriesEntity categoriesEntity = categoryRepository.findById(transactionDTO.getCategoryId())
                .orElseThrow(() -> {
                    return new CategoryDontExits("Không tồn tại");
                });

        TransactionEntity transactionEntity = TransactionEntity.builder().category(categoriesEntity).user_id(user)
                .amount(transactionDTO.getAmount()).note(transactionDTO.getNote()).build();

        transactionRepository.save(transactionEntity);

        return new ResponseData(HttpStatus.CREATED.value(), "Thêm giao dịch thành công", transactionEntity);
    }

    @Override
    public ResponseData editTransaction(TransactionEditDTO transactionEditDTO) {
        TransactionEntity transaction = transactionRepository.findById(transactionEditDTO.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        CategoriesEntity category = categoryRepository.findById(transactionEditDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (transactionEditDTO.getNote() != null) {
            transaction.setNote(transactionEditDTO.getNote());
        }
        if (transactionEditDTO.getAmount() != null) {
            transaction.setAmount(transactionEditDTO.getAmount());
        }

        transaction.setCategory(category);

        TransactionEntity updated = transactionRepository.save(transaction);

        return new ResponseData(HttpStatus.CREATED.value(), "Cập nhập thành công", updated);
    }

    @Override
    public ResponseData deleteTransaction(long id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseData(HttpStatus.OK.value(), "Xóa thành công");
        } catch (Exception e) {
            return new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Xóa không thành công");
        }
    }

    @Override
    public ResponseData getTransaction(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransaction'");
    }

}
