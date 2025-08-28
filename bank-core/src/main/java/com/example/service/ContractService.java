package com.example.service;


import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.dto.request.ContractRequestDto;
import com.example.enums.ContractStatus;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    ContractDetailResponseDto createAccount(AccountRequestDto accountRequestDto, UUID personId);
    AccountDetailResponseDto getAccountById (UUID accountId);
    List<AccountDetailResponseDto> getAllAccounts();
    AccountDetailResponseDto updateAccount(UUID accountId, AccountRequestDto accountRequestDto);
    void deleteAccount(UUID accountId);
    ContractDetailResponseDto updatedContract(UUID contractId, ContractRequestDto contractRequestDto);
    void changeContractStatus(UUID contractId, ContractStatus contractStatus);
}

