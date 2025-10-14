package com.example.specidication;

import com.example.dto.request.LedgerEntryRequestDto;
import com.example.entity.LedgerEntry;
import org.springframework.data.jpa.domain.Specification;

public class LedgerSpecification {
    public static Specification<LedgerEntry> ledgerEntrySpecification(LedgerEntryRequestDto ledgerEntryRequestDto){
        return ((root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction ();
            if(ledgerEntryRequestDto.getAmount () != null){
                predicate.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("Amount")),
                                "%"+ledgerEntryRequestDto.getAmount()+"%")
                );
            }
            if(ledgerEntryRequestDto.getEntryType () != null){
                predicate.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("EntryType")),
                                "%"+ledgerEntryRequestDto.getEntryType()+"%")
                );
            }
            if (ledgerEntryRequestDto.getAccountId () != null){
                predicate.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("AccountId")),
                                "%"+ledgerEntryRequestDto.getAccountId()+"%")
                );
            }
            if(ledgerEntryRequestDto.getTransactionId () != null){
                predicate.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("TransactionId")),
                                "%"+ledgerEntryRequestDto.getTransactionId()+"%")
                );
            }
            return predicate;
        });
    }
}
