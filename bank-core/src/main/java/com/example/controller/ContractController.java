package com.example.controller;

import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.request.ContractRequestDto;
import com.example.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/personId/{personId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<ContractDetailResponseDto> createContract(
            @PathVariable UUID personId,
            @RequestBody ContractRequestDto contractRequestDto) {
        return ResponseEntity.ok (contractService.createContract (contractRequestDto, personId));
    }

    @GetMapping("/{contractId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<ContractDetailResponseDto> getContractById(@PathVariable UUID contractId) {
        return ResponseEntity.ok (contractService.getContractById (contractId));
    }

    @GetMapping
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<List<ContractDetailResponseDto>> getAllContracts() {
        return ResponseEntity.ok (contractService.getAllContracts());
    }

    @PutMapping("/{contractId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<ContractDetailResponseDto> updateContract(
            @PathVariable UUID contractId,
            @RequestBody ContractRequestDto contractRequestDto){
        return ResponseEntity.ok (contractService.updatedContract (contractId,contractRequestDto));
    }

    @DeleteMapping("/{idContract}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<ContractDetailResponseDto> deleteContract(@PathVariable UUID idContract) {
        contractService.deleteContract (idContract);
        return ResponseEntity.noContent ().build ();
    }

    @PatchMapping("/{contractId}/status")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<ContractDetailResponseDto> changeContractStatus(
            @PathVariable UUID contractId,
            @RequestBody ContractRequestDto contractRequestDto){
        contractService.changeContractStatus (contractId,contractRequestDto.getStatus ());
        return ResponseEntity.noContent ().build ();
    }

    @PostMapping("/search")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('CLIENT')")
    public ResponseEntity<List<ContractDetailResponseDto>> searchContracts(@RequestBody ContractRequestDto contractRequestDto){
        return ResponseEntity.ok (contractService.searchContract (contractRequestDto));
    }
}
