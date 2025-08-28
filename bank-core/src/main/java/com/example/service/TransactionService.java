package com.example.service;


import com.example.dto.detail.TransactionDetailResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDetailResponseDto transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);
    TransactionDetailResponseDto deposit(UUID accountId, BigDecimal amount);
    TransactionDetailResponseDto withdraw(UUID accountId, BigDecimal amount);
    TransactionDetailResponseDto getTransactionById(UUID transactionId);
    List<TransactionDetailResponseDto> getAllTransactions();
    List<TransactionDetailResponseDto> getTransactionsByAccountId(UUID accountId);
}
