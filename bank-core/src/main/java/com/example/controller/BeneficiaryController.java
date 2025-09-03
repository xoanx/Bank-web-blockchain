package com.example.controller;

import com.example.dto.detail.BeneficiaryDetailResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;
import com.example.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @GetMapping
    public ResponseEntity<List<BeneficiaryDetailResponseDto>> getAllBeneficiaries() {
        return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries());
    }

    @PutMapping("/{beneficiaryId")
    public ResponseEntity<BeneficiaryDetailResponseDto> updatedBeneficiary(
            @PathVariable UUID beneficiaryId,
            @RequestBody BeneficiaryRequestDto beneficiaryRequestDto){
        return ResponseEntity.ok (beneficiaryService.updatedBeneficiary (beneficiaryId,beneficiaryRequestDto));
    }

    @PostMapping
    public ResponseEntity<BeneficiaryDetailResponseDto> addBeneficiary(@RequestBody BeneficiaryRequestDto requestDto) {
        return ResponseEntity.ok(beneficiaryService.addBeneficiary(requestDto));
    }

    @DeleteMapping("/{beneficiaryId}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable UUID beneficiaryId) {
        beneficiaryService.deleteBeneficiary(beneficiaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{beneficiaryId}")
    public ResponseEntity<BeneficiaryDetailResponseDto> getBeneficiaryById(@PathVariable UUID beneficiaryId) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(beneficiaryId));
    }
}
