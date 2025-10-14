package com.example.repository;

import com.example.entity.Account;
import com.example.enums.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUsername(String username);

    List<Account> findByUsernameContaining(String username);

    Optional<Account> findByPersonId(UUID personId);

    List<Account> findByAccountStatus(AccountStatus accountStatus);

    @Query("SELECT a FROM Account a WHERE a.balance < :amount")
    List<Account> findByBalanceAccounts(@Param ("amount") BigDecimal amount);
}
