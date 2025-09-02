package com.example.controller;

import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.request.ContractRequestDto;
import com.example.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/personId/{personId}")
    public ResponseEntity<ContractDetailResponseDto> createContract(
            @PathVariable UUID personId,
            @RequestBody ContractRequestDto contractRequestDto) {
        return ResponseEntity.ok (contractService.createContract (contractRequestDto, personId));
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<ContractDetailResponseDto> getContractById(UUID contractId) {
        return ResponseEntity.ok (contractService.getContractById (contractId));
    }

    @GetMapping
    public ResponseEntity<List<ContractDetailResponseDto>> getAllContracts() {
        return ResponseEntity.ok (contractService.getAllContracts());
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<ContractDetailResponseDto> updateContract(
            @PathVariable UUID contractId,
            @RequestBody ContractRequestDto contractRequestDto){
        return ResponseEntity.ok (contractService.updatedContract (contractId,contractRequestDto));
    }

    @DeleteMapping("/{idContract}")
    public ResponseEntity<ContractDetailResponseDto> deleteContract(@PathVariable UUID idContract) {
        contractService.deleteContract (idContract);
        return ResponseEntity.noContent ().build ();
    }

    @PatchMapping("/{contractId}")
    public ResponseEntity<ContractDetailResponseDto> changeContractStatus(
            @PathVariable UUID contractId,
            @RequestBody ContractRequestDto contractRequestDto){
        contractService.changeContractStatus (contractId,contractRequestDto.getStatus ());
        return ResponseEntity.noContent ().build ();
    }

    @PostMapping("/search")
    public ResponseEntity<List<ContractDetailResponseDto>> searchContracts(@RequestBody ContractRequestDto contractRequestDto){
        return ResponseEntity.ok (contractService.searchContract (contractRequestDto));
    }
}
