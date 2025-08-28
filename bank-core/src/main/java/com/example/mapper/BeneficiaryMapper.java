package com.example.mapper;

import com.example.dto.detail.BeneficiaryDetailResponseDto;
import com.example.dto.lite.BeneficiaryLiteResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;
import com.example.entity.Account;
import com.example.entity.Beneficiary;

public class BeneficiaryMapper {
    //map to Lite Response
    public static BeneficiaryLiteResponseDto beneficiaryLiteResponseDto(Beneficiary beneficiary) {
        if (beneficiary == null) {return null;}
        return BeneficiaryLiteResponseDto.builder ()
                .beneficiaryId (beneficiary.getId ())
                .beneficiaryName (beneficiary.getBeneficiaryName ())
                .aliasName (beneficiary.getAliasName ())
                .build ();
    }
    //map to Detail Response
    public static BeneficiaryDetailResponseDto beneficiaryDetailResponseDto(Beneficiary beneficiary) {
        if (beneficiary == null) {return null;}
        return BeneficiaryDetailResponseDto.builder ()
                .beneficiaryId (beneficiary.getId ())
                .beneficiaryName (beneficiary.getBeneficiaryName ())
                .beneficiaryAccountNumber (beneficiary.getBeneficiaryAccountNumber ())
                .bankCode (beneficiary.getBankCode ())
                .aliasName (beneficiary.getAliasName ())
                .ownerAccount (AccountMapper.accountMapToLite (beneficiary.getOwnerAccount ()))
                .build ();
    }
    //map to Entity
    public static Beneficiary mapToBeneficiary(BeneficiaryRequestDto beneficiaryRequestDto, Account account) {
        if (beneficiaryRequestDto == null) {return null;}
        return Beneficiary.builder ()
                .bankCode (beneficiaryRequestDto.getBankCode ())
                .aliasName (beneficiaryRequestDto.getAliasName ())
                .ownerAccount (account)
                .build ();
    }
}
