package com.example.service.impl;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Account;
import com.example.entity.Person;
import com.example.enums.AccountStatus;
import com.example.mapper.AccountMapper;
import com.example.repository.AccountRepository;
import com.example.repository.PersonRepository;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Override
    public AccountDetailResponseDto createAccount(AccountRequestDto accountRequestDto, UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found: " + personId));
        Account account = AccountMapper.mapToAccount(accountRequestDto, person);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBalance(account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.accountMapToDetail(savedAccount);
    }
    @Override
    public AccountDetailResponseDto deposit(BigDecimal amount, UUID personId) {
        Account account = accountRepository.findById (personId)
                .orElseThrow (() -> new RuntimeException("Account not found: " + personId));
        if (account.getAccountStatus() == AccountStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }
        account.setBalance(account.getBalance().add(amount));
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.accountMapToDetail(savedAccount);
    }
    @Override
    public AccountDetailResponseDto withdraw(UUID accountId, BigDecimal amount){
        Account account = accountRepository.findById (accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Account balance is not enough balance to transfer to another");
        }
        account.setBalance(account.getBalance().subtract(amount));
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.accountMapToDetail(savedAccount);
    }
    @Override
    public List<AccountDetailResponseDto> searchAccount(AccountRequestDto accountRequestDto){
        List<Account> accounts = accountRepository.findAll ()
                .stream ()
                .filter (a -> (accountRequestDto.getAccountNumber () == null || a.getAccountNumber ().equalsIgnoreCase (accountRequestDto.getAccountNumber ())))
                .filter (a -> (accountRequestDto.getStatus () == null || a.getAccountStatus ().equals(accountRequestDto.getStatus ())))
                .filter (a -> (accountRequestDto.getRole () == null || a.getRole ().equals (accountRequestDto.getRole ())))
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
        account.
    }
}
