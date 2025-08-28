package com.example.repository;

import com.example.entity.Contract;
import com.example.enums.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Optional<Contract> findByContractAddress(String contractAddress);

    List<Contract> findByStatus(ContractStatus status);

    List<Contract> findByPersonId(UUID personId);

    Optional<Contract> findByContractName(String contractName);

}
