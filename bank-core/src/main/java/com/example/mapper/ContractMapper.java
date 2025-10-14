package com.example.mapper;

import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import com.example.dto.request.ContractRequestDto;
import com.example.entity.Contract;
import com.example.entity.Person;
import com.example.enums.ContractStatus;

import java.time.LocalDateTime;

public class ContractMapper {
    //map to Lite Response
    public static ContractLiteResponseDto contractMapToLite(Contract contract) {
        if (contract == null) {return null;}
        return ContractLiteResponseDto.builder()
                .idContract(contract.getIdContract())
                .contractName(contract.getContractName())
                .status(contract.getStatus())
                .build();
    }
    //map to Detail Response
    public static ContractDetailResponseDto contractMapToDetail(Contract contract) {
        if (contract == null) {return null;}
        return ContractDetailResponseDto.builder()
                .idContract(contract.getIdContract())
                .contractAddress(contract.getContractAddress())
                .contractName(contract.getContractName())
                .message(contract.getMessage())
                .status(contract.getStatus())
                .createdAt(contract.getCreatedAt())
                .updatedAt(contract.getUpdateAt ())
                .personDetail (contract.getPerson () != null
                        ? PersonMapper.personMapToLite (contract.getPerson ())
                        : null)
                .build();
    }
    //map to Entity
    public static Contract mapToContract(ContractRequestDto contractRequestDto, Person person) {
        if (contractRequestDto == null) {return null;}
        return Contract.builder ()
                .contractAddress (contractRequestDto.getContractAddress ())
                .contractName (contractRequestDto.getContractName ())
                .message (contractRequestDto.getMessage ())
                .createdAt (LocalDateTime.now ())
                .updateAt (null)
                .status (contractRequestDto.getStatus () != null
                        ? contractRequestDto.getStatus () : ContractStatus.DRAFT)
                .person (person)
                .build ();
    }
}
