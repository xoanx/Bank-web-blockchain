package com.example.service;

import com.example.dto.detail.BeneficiaryDetailResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;

import java.util.List;
import java.util.UUID;

public interface BeneficiaryService {
    List<BeneficiaryDetailResponseDto> getAllBeneficiaries();
    BeneficiaryDetailResponseDto updatedBeneficiary(UUID beneficiaryId, BeneficiaryRequestDto beneficiaryRequestDto);
    void deleteBeneficiary(UUID beneficiaryId);
    BeneficiaryDetailResponseDto getBeneficiaryById(UUID beneficiaryId);
    BeneficiaryDetailResponseDto addBeneficiary(BeneficiaryRequestDto beneficiaryRequestDto);
}
