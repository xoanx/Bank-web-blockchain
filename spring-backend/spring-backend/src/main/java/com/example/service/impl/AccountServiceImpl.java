package com.example.service.impl;

import com.example.dto.AccountDto;
import com.example.entity.Account;
import com.example.mapper.AccountMapper;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(UUID idAccount) {
        Account account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto updateAccount(UUID idAccount, AccountDto accountDto) {
        Account existing = accountRepository.findById(idAccount)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Account updated = AccountMapper.mapToAccount(accountDto);
        updated.setIdAccount(existing.getIdAccount());
        return AccountMapper.mapToAccountDto(accountRepository.save(updated));
    }

    @Override
    public void deleteAccount(UUID idAccount) {
        accountRepository.deleteById(idAccount);
    }
}
