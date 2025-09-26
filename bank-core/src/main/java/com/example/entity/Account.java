package com.example.entity;

import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Account")
    private UUID idAccount;
    @Column(name = "Account_Number", length = 50, nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "User_Name", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "Password_Hash", nullable = false)
    private String passwordHash;
    @Column(name = "Log_In")
    private LocalDateTime logInAt;
    @Column(name = "Log_Out")
    private LocalDateTime logOutAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private AccountStatus accountStatus;
    @Column(name = "tokenVersion", nullable = false)
    private Integer tokenVersion = 0;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "User_Role", nullable = false)
//    private UserRole role;
    @Column(name = "balance", precision = 19, scale = 2, nullable = false)
    private BigDecimal balance;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "blockchain_address", length = 50, nullable = false)
    private String blockchainAddress;

    @ManyToOne
    @JoinColumn(name = "owner_person", nullable = false)
    @JsonBackReference
    private Person person;
}
