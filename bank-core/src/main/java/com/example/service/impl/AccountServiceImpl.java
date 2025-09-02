package com.example.service.impl;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Account;
import com.example.entity.LedgerEntry;
import com.example.entity.Person;
import com.example.entity.Transaction;
import com.example.enums.AccountStatus;
import com.example.enums.EntryType;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import com.example.mapper.AccountMapper;
import com.example.random.AccountNumberGenerator;
import com.example.repository.AccountRepository;
import com.example.repository.LedgerEntryRepository;
import com.example.repository.PersonRepository;
import com.example.repository.TransactionRepository;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    @Override
    public AccountDetailResponseDto createAccount(AccountRequestDto accountRequestDto, UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found: " + personId));
        Account account = AccountMapper.mapToAccount(accountRequestDto, person);
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber ());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBalance(account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.accountMapToDetail(savedAccount);
    }
//    @Override
//    public AccountDetailResponseDto deposit(BigDecimal amount, UUID accountId) {
//        Account account = accountRepository.findById (accountId)
//                .orElseThrow (() -> new RuntimeException("Account not found: " + accountId));
//        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
//            throw new RuntimeException("Account is not active");
//        }
//        account.setBalance(account.getBalance().add(amount));
//        Transaction transaction = Transaction.builder ()
//                .reference (UUID.randomUUID ().toString ())
//                .amount (amount)
//                .type (TransactionType.DEPOSIT)
//                .status (TransactionStatus.SUCCESS)
//                .createdAt (LocalDateTime.now ())
//                .fromAccount (null)
//                .toAccount (account)
//                .build ();
//        Transaction savedTransaction = transactionRepository.save(transaction);
//        LedgerEntry ledgerEntry = LedgerEntry.builder ()
//                .amount (amount)
//                .entryType (En)
//                .build ();
//        Account savedAccount = accountRepository.save(account);
//        return AccountMapper.accountMapToDetail(savedAccount);
//    }
    @Override
    public List<AccountDetailResponseDto> searchAccount(AccountRequestDto accountRequestDto){
        List<Account> accounts = accountRepository.findAll ()
                .stream ()
                .filter (a -> (accountRequestDto.getAccountNumber () == null
                        || a.getAccountNumber ().equalsIgnoreCase (accountRequestDto.getAccountNumber ())))
                .filter (a -> (accountRequestDto.getStatus () == null
                        || a.getAccountStatus ().equals(accountRequestDto.getStatus ())))
                .filter (a -> (accountRequestDto.getRole () == null
                        || a.getRole ().equals (accountRequestDto.getRole ())))
                .toList ();
        return accounts.stream ()
                .map (AccountMapper::accountMapToDetail)
                .toList ();
    }
    @Override
    public List<AccountDetailResponseDto> getAllAccounts(){
        return accountRepository.findAll ()
                .stream ()
                .map (AccountMapper::accountMapToDetail)
                .toList ();
    }
    @Override
    public AccountDetailResponseDto updateAccount(UUID accountId, AccountRequestDto accountRequestDto){
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        //field request
        account.setUsername (accountRequestDto.getUsername());
        account.setPasswordHash (accountRequestDto.getPassword ());
        account.setRole (accountRequestDto.getRole ());
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.accountMapToDetail(savedAccount);
    }
    @Override
    public void deleteAccount(UUID accountId) {
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        accountRepository.delete (account);
    }
    @Override
    public void activateAccount(UUID accountId) {
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        account.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }
    @Override
    public void deactivateAccount(UUID accountId) {
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        account.setAccountStatus(AccountStatus.INACTIVE);
        accountRepository.save(account);
    }
//    @Override
//    public AccountDetailResponseDto withdraw(UUID accountId, BigDecimal amount){
//        Account account = accountRepository.findById (accountId)
//                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
//        if(account.getAccountStatus () != AccountStatus.ACTIVE) {
//            throw new RuntimeException("Account is not active");
//        }
//        if (account.getBalance ().compareTo(amount) < 0) {
//            throw new RuntimeException("Account balance is not enough balance to transfer to another");
//        }
//        account.setBalance (account.getBalance ().subtract (amount));
//        Transaction transaction = Transaction.builder ()
//                .reference (UUID.randomUUID ().toString ())
//                .amount (amount)
//                .type (TransactionType.WITHDRAW)
//                .status (TransactionStatus.SUCCESS)
//                .createdAt (LocalDateTime.now ())
//                .fromAccount (account)
//                .build ();
//        Transaction savedTransaction = transactionRepository.save (transaction);
//        LedgerEntry ledgerEntry = LedgerEntry.builder()
//                .amount (amount)
//                .entryType (EntryType.DEBIT)
//                .balanceAfter (account.getBalance ())
//                .createdAt (LocalDateTime.now ())
//                .account (account)
//                .transaction (savedTransaction)
//                .build();
//        ledgerEntryRepository.save (ledgerEntry);
//        Account savedAccount = accountRepository.save(account);
//        return AccountMapper.accountMapToDetail(savedAccount);
//    }
    @Override
    public AccountDetailResponseDto withdraw(UUID accountId, BigDecimal amount) {
        throw new UnsupportedOperationException("Use TransactionService for withdraw");
    }
    @Override
    public AccountDetailResponseDto deposit(BigDecimal amount, UUID accountId) {
        throw new UnsupportedOperationException("Use TransactionService for deposit");
    }
}
