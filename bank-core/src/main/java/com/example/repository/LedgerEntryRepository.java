package com.example.repository;

import com.example.entity.LedgerEntry;
import com.example.enums.EntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, UUID>, JpaSpecificationExecutor<LedgerEntry> {
    List<LedgerEntry> findByTransactionId(UUID transactionId);

    List<LedgerEntry> findByAccount_IdAccount(UUID accountId);

    List<LedgerEntry> findByEntryType(EntryType entryType);

    @Query ("SELECT l FROM LedgerEntry l WHERE l.createdAt BETWEEN :start AND :end")
    List<LedgerEntry> findLedgerEntriesWithinPeriod(@Param ("start") LocalDateTime start,
                                                    @Param("end") LocalDateTime end);

}
