package com.example.service.impl;

import com.example.entity.Account;
import com.example.entity.Transaction;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import com.example.repository.AccountRepository;
import com.example.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction transfer(UUID fromAccountId, UUID toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow ((Supplier<RuntimeException>) () -> new RuntimeException("Sender not found"));
        Account toAccount = accountRepository.findById (toAccountId)
                .orElseThrow ((Supplier<RuntimeException>) () -> new RuntimeException("Receiver not found"));
        if()
        if(fromAccount.getBalance () < amount){
            throw new RuntimeException("Sender account balance is less than amount");
        }
        fromAccount.setBalance (fromAccount.getBalance () - amount);
        accountRepository.save(fromAccount);
        toAccount.setBalance (toAccount.getBalance () + amount);
        accountRepository.save(toAccount);
        Transaction transaction = Transaction.builder()
                .fromAccount (fromAccount)
                .toAccount (toAccount)
                .amount (amount)
                .type (TransactionType.TRANSFER)
                .status (TransactionStatus.SUCCESS)
                .build();
        return transactionRepository.save(transaction);
    }
}
