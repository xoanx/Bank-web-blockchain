package com.example.service;


import com.example.dto.detail.AccountDetailResponseDto;
import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.dto.request.ContractRequestDto;
import com.example.enums.ContractStatus;
import com.example.specidication.ContractSpecification;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    ContractDetailResponseDto createContract(ContractRequestDto contractRequestDto, UUID personId);
    ContractDetailResponseDto getContractById (UUID contractId);
    List<ContractDetailResponseDto> getAllContracts();
    ContractDetailResponseDto updatedContract(UUID contractId, ContractRequestDto contractRequestDto);
    void deleteContract(UUID contractId);
    void changeContractStatus(UUID contractId, ContractStatus contractStatus);
    List<ContractDetailResponseDto> searchContract(ContractRequestDto contractRequestDto);
}

