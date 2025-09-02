package com.example.service.impl;

import com.example.dto.detail.TransactionDetailResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;
import com.example.dto.request.TransactionRequestDto;
import com.example.entity.Account;
import com.example.entity.LedgerEntry;
import com.example.entity.Transaction;
import com.example.enums.AccountStatus;
import com.example.enums.EntryType;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import com.example.mapper.TransactionMapper;
import com.example.repository.AccountRepository;
import com.example.repository.LedgerEntryRepository;
import com.example.repository.TransactionRepository;
import com.example.service.BeneficiaryService;
import com.example.service.TransactionService;
import com.example.specidication.TransactionSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final LedgerEntryRepository ledgerEntryRepository;
    private final BeneficiaryService beneficiaryService;

    @Transactional
    public TransactionDetailResponseDto transfer(TransactionRequestDto transactionRequestDto, boolean saveBeneficiary) {
        if(transactionRequestDto.getFromAccount ().equals(transactionRequestDto.getToAccount ())) {
            throw new RuntimeException ("Cannnot transfer to the same account");
        }
        Account fromAccount = accountRepository.findById(transactionRequestDto.getFromAccount ().getIdAccount ())
                .orElseThrow ((Supplier<RuntimeException>) () -> new RuntimeException("Sender not found"));
        Account toAccount = accountRepository.findById (transactionRequestDto.getToAccount ().getIdAccount ())
                .orElseThrow ((Supplier<RuntimeException>) () -> new RuntimeException("Receiver not found"));
        if(fromAccount.getAccountStatus () != AccountStatus.ACTIVE){
            throw new RuntimeException ("From account is not ACTIVE");
        }
        if(toAccount.getAccountStatus () != AccountStatus.ACTIVE){
            throw new RuntimeException ("To account is not ACTIVE");
        }
        if(fromAccount.getBalance ().compareTo (transactionRequestDto.getAmount ()) < 0){
            throw new RuntimeException ("From account balance is less than amount");
        }
        fromAccount.setBalance (fromAccount.getBalance ().subtract (transactionRequestDto.getAmount ()));
        accountRepository.save(fromAccount);
        toAccount.setBalance (toAccount.getBalance ().add (transactionRequestDto.getAmount ()));
        accountRepository.save(toAccount);
        Transaction transaction = Transaction.builder()
                .reference (UUID.randomUUID ().toString ())
                .fromAccount (fromAccount)
                .toAccount (toAccount)
                .amount (transactionRequestDto.getAmount ())
                .type (TransactionType.TRANSFER)
                .status (TransactionStatus.SUCCESS)
                .createdAt (LocalDateTime.now ())
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        ledgerEntryRepository.save (LedgerEntry.builder ()
                .amount (transactionRequestDto.getAmount ())
                .entryType(EntryType.DEBIT)
                .balanceAfter(fromAccount.getBalance())
                .createdAt(LocalDateTime.now())
                .account(fromAccount)
                .transaction(savedTransaction)
                .build ());
        ledgerEntryRepository.save(LedgerEntry.builder()
                .amount(transactionRequestDto.getAmount ())
                .entryType(EntryType.CREDIT)
                .balanceAfter(toAccount.getBalance())
                .createdAt(LocalDateTime.now())
                .account(toAccount)
                .transaction(savedTransaction)
                .build());
        //save benefication
        if(saveBeneficiary){
            beneficiaryService.addBeneficiary (
                    BeneficiaryRequestDto.builder ()
                            .ownerAccountId (fromAccount.getIdAccount ())
                            .beneficiaryAccountNumber (toAccount.getAccountNumber ())
                            .beneficiaryName (toAccount.getPerson ().getFullName ())
                            .bankCode ("AUTO-BANK-000")
                            .aliasName ("AUTO-BANK-001")
                            .build ());
        }
        return TransactionMapper.transactionDetailResponseDto (savedTransaction);
    }
    @Override
    public TransactionDetailResponseDto deposit(UUID accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }
        account.setBalance(account.getBalance().add(amount));
        Transaction transaction = Transaction.builder()
                .reference(UUID.randomUUID().toString())
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .toAccount(account)
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        ledgerEntryRepository.save(LedgerEntry.builder()
                .amount(amount)
                .entryType(EntryType.CREDIT)
                .balanceAfter(account.getBalance())
                .createdAt(LocalDateTime.now())
                .account(account)
                .transaction(savedTransaction)
                .build());
        return TransactionMapper.transactionDetailResponseDto (savedTransaction);
    }
    @Override
    public TransactionDetailResponseDto withdraw(UUID accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        Transaction transaction = Transaction.builder()
                .reference(UUID.randomUUID().toString())
                .amount(amount)
                .type(TransactionType.WITHDRAW)
                .status(TransactionStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .fromAccount(account)
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        ledgerEntryRepository.save(LedgerEntry.builder()
                .amount(amount)
                .entryType(EntryType.DEBIT)
                .balanceAfter(account.getBalance())
                .createdAt(LocalDateTime.now())
                .account(account)
                .transaction(savedTransaction)
                .build());

        return TransactionMapper.transactionDetailResponseDto (savedTransaction);
    }
    @Override
    public TransactionDetailResponseDto getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return TransactionMapper.transactionDetailResponseDto (transaction);
    }
    @Override
    public List<TransactionDetailResponseDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::transactionDetailResponseDto)
                .toList();
    }
    @Override
    public List<TransactionDetailResponseDto> getTransactionsByAccountId(UUID accountId) {
        return transactionRepository.findAll().stream()
                .filter(t -> (t.getFromAccount() != null && t.getFromAccount().getIdAccount().equals(accountId)) ||
                        (t.getToAccount() != null && t.getToAccount().getIdAccount().equals(accountId)))
                .map(TransactionMapper::transactionDetailResponseDto)
                .toList();
    }
    @Override
    public List<TransactionDetailResponseDto> searchTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionRepository.findAll(TransactionSpecification.transactionSpecification (transactionRequestDto))
                .stream()
                .map(TransactionMapper::transactionDetailResponseDto)
                .toList();
    }
}
