package com.example.mapper;

import com.example.dto.detail.TransactionDetailResponseDto;
import com.example.dto.lite.TransactionLiteResponseDto;
import com.example.entity.Account;
import com.example.entity.Transaction;

public class TransactionMapper {
    //map to Detail Response
    public static TransactionDetailResponseDto transactionDetailResponseDto(Transaction transaction) {
        if (transaction == null) {return null;}
        return TransactionDetailResponseDto.builder ()
                .transactionId (transaction.getId ())
                .reference (transaction.getReference ())
                .amount (transaction.getAmount ())
                .type (transaction.getType ())
                .status (transaction.getStatus ())
                .createdAt (transaction.getCreatedAt ())
                .blockchainTxHash (transaction.getBlockchainTxHash ())
                .fromAccount (AccountMapper.accountMapToLite (transaction.getFromAccount ()))
                .toAccount (AccountMapper.accountMapToLite (transaction.getToAccount ()))
                .build ();
    }
    //map to Lite Response
    public static TransactionLiteResponseDto transactionLiteResponseDto(Transaction transaction) {
        if (transaction == null) {return null;}
        return TransactionLiteResponseDto.builder ()
                .transactionId (transaction.getId ())
                .reference (transaction.getReference ())
                .amount (transaction.getAmount ())
                .status (transaction.getStatus ())
                .build ();
    }
    //map to Entity
    public static Transaction mapToEntity(TransactionLiteResponseDto transactionLiteResponseDto) {
        if (transactionLiteResponseDto == null) {return null;}
        return Transaction.builder ()
                .id (transactionLiteResponseDto.getTransactionId ())
                .reference (transactionLiteResponseDto.getReference ())
                .amount (transactionLiteResponseDto.getAmount ())
                .status (transactionLiteResponseDto.getStatus ())
                .build ();
    }
}
