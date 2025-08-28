package com.example.mapper;

import com.example.dto.detail.LedgerEntryDetailResponseDto;
import com.example.dto.lite.LedgerEntryLiteResponseDto;
import com.example.dto.request.LedgerEntryRequestDto;
import com.example.entity.Account;
import com.example.entity.LedgerEntry;
import com.example.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LedgerMapper {
    //map to Detail Response
    public static LedgerEntryDetailResponseDto ledgerEntryDetailResponseDto(LedgerEntry ledgerEntry) {
        if (ledgerEntry == null) {return null;}
        return LedgerEntryDetailResponseDto.builder ()
                .id(ledgerEntry.getId ())
                .amount (ledgerEntry.getAmount ())
                .balanceAfter (ledgerEntry.getBalanceAfter ())
                .entryType (ledgerEntry.getEntryType ())
                .createdAt (ledgerEntry.getCreatedAt ())
                .transaction (TransactionMapper.transactionLiteResponseDto (ledgerEntry.getTransaction ()))
                .account (AccountMapper.accountMapToLite (ledgerEntry.getAccount ()))
                .build ();
    }
    //map to Lite Response
    public static LedgerEntryLiteResponseDto ledgerEntryLiteResponseDto(LedgerEntry ledgerEntry) {
        if (ledgerEntry == null) {return null;}
        return LedgerEntryLiteResponseDto.builder ()
                .ledgerEntryId (ledgerEntry.getId ())
                .amount (ledgerEntry.getAmount ())
                .entryType (ledgerEntry.getEntryType ())
                .build ();
    }
    //map to Entity
    public static LedgerEntry mapToLedgerEntry(LedgerEntryRequestDto ledgerEntryRequestDto, Account account, Transaction transaction) {
        if (ledgerEntryRequestDto == null) {return null;}
        return LedgerEntry.builder ()
                .amount (ledgerEntryRequestDto.getAmount ())
                .entryType (ledgerEntryRequestDto.getEntryType ())
                .account (account)
                .transaction (transaction)
                .createdAt (LocalDateTime.now ())
                .balanceAfter (BigDecimal.ZERO)
                .build ();
    }
}
