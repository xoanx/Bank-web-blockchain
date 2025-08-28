package com.example.repository;

import com.example.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, UUID> {

    List<Beneficiary> findByOwnerAccount_IdAccount(UUID ownerAccountId);

    Optional<Beneficiary> findByBeneficiaryAccountNumber(String accountNumber);

    List<Beneficiary> findByBankCode(String bankCode);

}
