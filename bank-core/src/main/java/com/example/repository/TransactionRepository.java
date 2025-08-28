package com.example.repository;

import com.example.entity.Transaction;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Component
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByReference(String reference);

    List<Transaction> findByFromAccountId(UUID fromAccountId);

    List<Transaction> findByToAccountId(UUID toAccountId);

    List<Transaction> findByStatus(TransactionStatus status);

    List<Transaction> findByType(TransactionType type);

    @Query ("SELECT t FROM Transaction t WHERE t.createdAt BETWEEN :start AND :end")
    List<Transaction> findTransactionsWithinPeriod(@Param ("start") LocalDateTime start,
                                                   @Param("end") LocalDateTime end);

}
