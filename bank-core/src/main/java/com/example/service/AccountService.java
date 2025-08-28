package com.example.service;

import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.request.AccountRequestDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDetailResponseDto createAccount(AccountRequestDto accountRequestDto, UUID personId);
    AccountDetailResponseDto deposit(BigDecimal amount, UUID personId);
    List<AccountDetailResponseDto> searchAccount (AccountRequestDto accountRequestDto);
    List<AccountDetailResponseDto> getAllAccounts();
    AccountDetailResponseDto updateAccount(UUID accountId, AccountRequestDto accountRequestDto);
    void deleteAccount(UUID accountId);
    void activateAccount(UUID accountId);
    void deactivateAccount(UUID accountId);
    AccountDetailResponseDto withdraw(UUID accountId, BigDecimal amount);
}
