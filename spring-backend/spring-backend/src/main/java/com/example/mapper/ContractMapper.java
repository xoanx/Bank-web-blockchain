package com.example.mapper;

import com.example.dto.ContractDto;
import com.example.entity.Contract;

public class ContractMapper {

    public static ContractDto mapToContractDto(Contract contract) {
        return ContractDto.builder()
                .idContract(contract.getIdContract())
                .contractAddress(contract.getContractAddress())
                .contractName(contract.getContractName())
                .message(contract.getMessage())
                .createdAt(contract.getCreatedAt())
                .lastUpdatedAt(contract.getLastUpdatedAt())
                .status(contract.getStatus())
                .build();
    }

    public static Contract mapToContract(ContractDto contractDto) {
        return Contract.builder()
                .idContract(contractDto.getIdContract())
                .contractAddress(contractDto.getContractAddress())
                .contractName(contractDto.getContractName())
                .message(contractDto.getMessage())
                .createdAt(contractDto.getCreatedAt())
                .lastUpdatedAt(contractDto.getLastUpdatedAt())
                .status(contractDto.getStatus())
                .build();
    }
}
