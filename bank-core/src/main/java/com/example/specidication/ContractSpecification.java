package com.example.specidication;

import com.example.dto.request.ContractRequestDto;
import com.example.entity.Contract;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

public class ContractSpecification {
    public static Specification<Contract> contractSpecification(ContractRequestDto contractRequestDto) {
        return ((root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();
            if (contractRequestDto.getContractName() != null) {
                predicates.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("contractName")), "%"
                                + contractRequestDto.getContractName().toLowerCase () + "%")
                );
            }
            if (contractRequestDto.getContractAddress() != null) {
                predicates.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("contractAddress")), "%"
                        + contractRequestDto.getContractAddress().toLowerCase () + "%")
                );
            }
            if (contractRequestDto.getMessage () != null){
                predicates.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("message")), "%"
                        + contractRequestDto.getMessage ().toLowerCase () + "%")
                );
            }
            if (contractRequestDto.getStatus () != null){
                predicates.getExpressions ().add (
                        criteriaBuilder.like (criteriaBuilder.lower (root.get ("status")), "%"
                        + contractRequestDto.getStatus ()+"%")
                );
            }
            if (contractRequestDto.getPersonId () != null){
                predicates.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("personId")), "%"
                        + contractRequestDto.getPersonId () + "%")
                );
            }
            if (contractRequestDto.getIdContract () != null){
                predicates.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("idContract")), "%"
                        + contractRequestDto.getIdContract () + "%")
                );
            }
            if (contractRequestDto.getAccountId () != null){
                predicates.getExpressions ().add (
                        criteriaBuilder.equal (criteriaBuilder.lower (root.get ("accountId")), "%"
                        + contractRequestDto.getAccountId () + "%")
                );
            }
            return predicates;
        }   );
    }
}
