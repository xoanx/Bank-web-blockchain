package com.example.service.impl;

import com.example.dto.detail.LedgerEntryDetailResponseDto;
import com.example.dto.request.LedgerEntryRequestDto;
import com.example.entity.Account;
import com.example.entity.LedgerEntry;
import com.example.entity.Transaction;
import com.example.mapper.LedgerMapper;
import com.example.repository.AccountRepository;
import com.example.repository.LedgerEntryRepository;
import com.example.repository.TransactionRepository;
import com.example.service.LedgerEntryService;
import com.example.specidication.LedgerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LedgerEntryServiceImpl implements LedgerEntryService {
    private final LedgerEntryRepository ledgerEntryRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public LedgerEntryDetailResponseDto createEntry(LedgerEntryRequestDto ledgerEntryRequestDto){
        Account account = accountRepository.findById(ledgerEntryRequestDto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Transaction transaction = transactionRepository.findById (ledgerEntryRequestDto.getTransactionId ())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if(ledgerEntryRequestDto.getAmount().compareTo (BigDecimal.ZERO)<0){
            throw new RuntimeException("Amount less than zero");
        }
        LedgerEntry ledgerEntry = LedgerEntry.builder ()
                .entryType (ledgerEntryRequestDto.getEntryType ())
                .amount (ledgerEntryRequestDto.getAmount ())
                .createdAt (LocalDateTime.now ())
                .balanceAfter(account.getBalance ())
                .account (account)
                .transaction (transaction)
                .build ();
        LedgerEntry savedLedgerEntry = ledgerEntryRepository.save(ledgerEntry);
        return LedgerMapper.ledgerEntryDetailResponseDto (savedLedgerEntry);
    }
    @Override
    public List<LedgerEntryDetailResponseDto> searchEntries(LedgerEntryRequestDto ledgerEntryRequestDto){
        return ledgerEntryRepository.findAll (LedgerSpecification.ledgerEntrySpecification (ledgerEntryRequestDto))
                .stream ()
                .map (LedgerMapper::ledgerEntryDetailResponseDto)
                .toList ();
    }
    @Override
    public List<LedgerEntryDetailResponseDto> getAllEntries(){
        return ledgerEntryRepository.findAll ()
                .stream ()
                .map (LedgerMapper::ledgerEntryDetailResponseDto)
                .toList ();
    }
    @Override
    public List<LedgerEntryDetailResponseDto> getEntriesByTransaction(UUID transactionId){
        Transaction transaction = transactionRepository.findById (transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return ledgerEntryRepository.findByTransactionId (transactionId)
                .stream ()
                .map (LedgerMapper::ledgerEntryDetailResponseDto)
                .toList ();
    }
    @Override
    public List<LedgerEntryDetailResponseDto> getEntriesByAccount(UUID accountId){
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return ledgerEntryRepository.findByAccount_IdAccount (accountId)
                .stream ()
                .map (LedgerMapper::ledgerEntryDetailResponseDto)
                .toList ();
    }

}
