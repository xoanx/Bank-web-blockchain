package com.example.controller;

import com.example.dto.detail.TransactionDetailResponseDto;
import com.example.dto.request.TransactionRequestDto;
import com.example.entity.Transaction;
import com.example.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{accountId}/transfer")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<TransactionDetailResponseDto> transfer(
            @RequestBody TransactionRequestDto transactionRequestDto,
            @RequestParam(defaultValue = "false") boolean saveBeneficiary) {
        return ResponseEntity.ok (transactionService.transfer(transactionRequestDto, saveBeneficiary));
    }

    @PostMapping("/{accountId}/deposit")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<TransactionDetailResponseDto> deposit(
            @PathVariable UUID accountId,
            @RequestParam BigDecimal amount ){
        return ResponseEntity.ok (transactionService.deposit(accountId, amount));
    }

    @PostMapping("/{accountId}/withdraw")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<TransactionDetailResponseDto> withdraw(
            @PathVariable UUID accountId,
            @RequestParam BigDecimal amount ){
        return ResponseEntity.ok (transactionService.withdraw(accountId, amount));
    }

    @GetMapping("/{transactionId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<TransactionDetailResponseDto> getTransactionById(@PathVariable UUID transactionId){
        return ResponseEntity.ok (transactionService.getTransactionById (transactionId));
    }

    @GetMapping
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('ADMIN')")
    public ResponseEntity<List<TransactionDetailResponseDto>> getAllTransactions(){
        return ResponseEntity.ok (transactionService.getAllTransactions());
    }

    @GetMapping("/{accountId}")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<List<TransactionDetailResponseDto>> getTransactionByAccountId(@PathVariable UUID accountId){
        return ResponseEntity.ok (transactionService.getTransactionsByAccountId (accountId));
    }

    @PostMapping("/search")
    @PreAuthorize ("hasRole('ADMIN') or hasRole('BANK') or hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<List<TransactionDetailResponseDto>> searchTransactions(TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok (transactionService.searchTransaction (transactionRequestDto));
    }
}
