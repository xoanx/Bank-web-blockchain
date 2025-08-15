package com.example.service;

import com.example.dto.AccountDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<AccountDto> getAllAccounts();
    AccountDto getAccountById(UUID idAccount);
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(UUID idAccount, AccountDto accountDto);
    void deleteAccount(UUID idAccount);
}
