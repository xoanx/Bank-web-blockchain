package com.example.controller;

import com.example.dto.detail.TransactionDetailResponseDto;
import com.example.dto.request.TransactionRequestDto;
import com.example.entity.Transaction;
import com.example.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDetailResponseDto> transfer(
            @RequestBody TransactionRequestDto transactionRequestDto,
            @RequestParam(defaultValue = "false") boolean saveBeneficiary) {
        return ResponseEntity.ok (transactionService.transfer(transactionRequestDto, saveBeneficiary));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDetailResponseDto> deposit(
            @PathVariable UUID accountId,
            @PathVariable BigDecimal amount ){
        return ResponseEntity.ok (transactionService.deposit(accountId, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDetailResponseDto> withdraw(
            @PathVariable UUID accountId,
            @PathVariable BigDecimal amount ){
        return ResponseEntity.ok (transactionService.withdraw(accountId, amount));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDetailResponseDto> getTransactionById(@PathVariable UUID transactionId){
        return ResponseEntity.ok (transactionService.getTransactionById (transactionId));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDetailResponseDto>> getAllTransactions(){
        return ResponseEntity.ok (transactionService.getAllTransactions());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDetailResponseDto>> getTransactionByAccountId(@PathVariable UUID accountId){
        return ResponseEntity.ok (transactionService.getTransactionsByAccountId (accountId));
    }

    @PostMapping("/search")
    public ResponseEntity<List<TransactionDetailResponseDto>> searchTransactions(TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok (transactionService.searchTransaction (transactionRequestDto));
    }
}
