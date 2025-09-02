package com.example.specidication;

import com.example.dto.request.TransactionRequestDto;
import com.example.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

public class TransactionSpecification {
    public static Specification<Transaction> transactionSpecification (TransactionRequestDto transactionRequestDto) {
        return ((root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction ();
            if (transactionRequestDto.getFromAccount () != null) {
                predicate.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("fromAccount")),
                                "%" + transactionRequestDto.getFromAccount () + "%")
                );
            }
            if (transactionRequestDto.getToAccount () != null) {
                predicate.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("toAccount")),
                                "%" + transactionRequestDto.getToAccount () + "%")
                );
            }
            if (transactionRequestDto.getAmount () != null) {
                predicate.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("amount")),
                                "%" + transactionRequestDto.getAmount () + "%")
                );
            }
            if (transactionRequestDto.getType () != null) {
                predicate.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("type")),
                                "%" + transactionRequestDto.getType () + "%")
                );
            }
            return predicate;
        });
    }
}