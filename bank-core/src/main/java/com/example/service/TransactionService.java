package com.example.service;


import com.example.dto.detail.TransactionDetailResponseDto;
import com.example.dto.request.TransactionRequestDto;
import com.example.specidication.TransactionSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDetailResponseDto transfer(TransactionRequestDto transactionRequestDto, boolean saveBeneficiary);
    TransactionDetailResponseDto deposit(UUID accountId, BigDecimal amount);
    TransactionDetailResponseDto withdraw(UUID accountId, BigDecimal amount);
    TransactionDetailResponseDto getTransactionById(UUID transactionId);
    List<TransactionDetailResponseDto> getAllTransactions();
    List<TransactionDetailResponseDto> getTransactionsByAccountId(UUID accountId);
    List<TransactionDetailResponseDto> searchTransaction(TransactionRequestDto transactionRequestDto);
}
