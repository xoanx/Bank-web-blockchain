package com.example.service.impl;

import com.example.dto.detail.BeneficiaryDetailResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;
import com.example.entity.Account;
import com.example.entity.Beneficiary;
import com.example.mapper.BeneficiaryMapper;
import com.example.repository.AccountRepository;
import com.example.repository.BeneficiaryRepository;
import com.example.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final AccountRepository accountRepository;

//    @Override
//    public BeneficiaryDetailResponseDto addBeneficiary(UUID ownerAccountId){
//        Account owner = accountRepository.findById(ownerAccountId)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//        Beneficiary beneficiary = Beneficiary.builder ()
//                .beneficiaryName ("New Beneficiary")
//                .beneficiaryAccountNumber ("00000")
//                .bankCode ("DEFAULT_BANK")
//                .aliasName ("ALIAS"+UUID.randomUUID ())
//                .ownerAccount (owner)
//                .build ();
//        Beneficiary saved = beneficiaryRepository.save(beneficiary);
//        return BeneficiaryMapper.beneficiaryDetailResponseDto (saved);
//    }
    @Override
    public List<BeneficiaryDetailResponseDto> getAllBeneficiaries() {
        return beneficiaryRepository.findAll().stream()
                .map(BeneficiaryMapper::beneficiaryDetailResponseDto)
                .toList();
    }
    @Override
    public BeneficiaryDetailResponseDto updatedBeneficiary(UUID beneficiaryId, BeneficiaryRequestDto beneficiaryRequestDto) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found: " + beneficiaryId));
        Account account = accountRepository.findById (beneficiaryRequestDto.getOwnerAccountId ())
                        .orElseThrow (() -> new RuntimeException("Owner not found" ));
        beneficiary.setBeneficiaryName(beneficiaryRequestDto.getBeneficiaryName ());
        beneficiary.setBeneficiaryAccountNumber(beneficiaryRequestDto.getBeneficiaryAccountNumber());
        beneficiary.setBankCode(beneficiaryRequestDto.getBankCode());
        beneficiary.setAliasName(beneficiaryRequestDto.getAliasName());

        Beneficiary saved = beneficiaryRepository.save(beneficiary);
        return BeneficiaryMapper.beneficiaryDetailResponseDto (saved);
    }
    @Override
    public void deleteBeneficiary(UUID beneficiaryId) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found: " + beneficiaryId));
        beneficiaryRepository.delete(beneficiary);
    }
    @Override
    public BeneficiaryDetailResponseDto getBeneficiaryById(UUID beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId)
                .map(BeneficiaryMapper::beneficiaryDetailResponseDto)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found"));
    }
    @Override
    public BeneficiaryDetailResponseDto addBeneficiary(BeneficiaryRequestDto requestDto) {
        Account owner = accountRepository.findById(requestDto.getOwnerAccountId ())
                .orElseThrow(() -> new RuntimeException("Owner account not found"));
        Beneficiary beneficiary = BeneficiaryMapper.mapToBeneficiary (requestDto, owner);
        Beneficiary saved = beneficiaryRepository.save(beneficiary);
        return BeneficiaryMapper.beneficiaryDetailResponseDto (saved);
    }
}
