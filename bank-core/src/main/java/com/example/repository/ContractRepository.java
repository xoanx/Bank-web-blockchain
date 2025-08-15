package com.example.repository;

import com.example.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Contract findByName(String name);
    Contract findByStatus(String status);
}
