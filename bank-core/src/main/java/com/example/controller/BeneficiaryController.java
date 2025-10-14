package com.example.controller;

import com.example.dto.detail.BeneficiaryDetailResponseDto;
import com.example.dto.request.BeneficiaryRequestDto;
import com.example.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @GetMapping
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<List<BeneficiaryDetailResponseDto>> getAllBeneficiaries() {
        return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries());
    }

    @PutMapping("/{beneficiaryId")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<BeneficiaryDetailResponseDto> updatedBeneficiary(
            @PathVariable UUID beneficiaryId,
            @RequestBody BeneficiaryRequestDto beneficiaryRequestDto){
        return ResponseEntity.ok (beneficiaryService.updatedBeneficiary (beneficiaryId,beneficiaryRequestDto));
    }

    @PostMapping
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<BeneficiaryDetailResponseDto> addBeneficiary(@RequestBody BeneficiaryRequestDto requestDto) {
        return ResponseEntity.ok(beneficiaryService.addBeneficiary(requestDto));
    }

    @DeleteMapping("/{beneficiaryId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable UUID beneficiaryId) {
        beneficiaryService.deleteBeneficiary(beneficiaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{beneficiaryId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<BeneficiaryDetailResponseDto> getBeneficiaryById(@PathVariable UUID beneficiaryId) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(beneficiaryId));
    }
}
