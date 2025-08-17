package com.example.entity;

import com.example.enums.AccountStatus;
import com.example.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
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
    @Column(name = "Account_Number", length = 50, nullable = false)
    private String accountNumber;
    @Column(name = "User_Name", length = 50, nullable = false)
    private String username;
    @Column(name = "Password_Hash", length = 50, nullable = false)
    private String passwordHash;
    @Column(name = "Log_In", length = 20, nullable = false)
    private LocalDateTime logInAt;
    @Column(name = "Log_Out", length = 20, nullable = false)
    private LocalDateTime  logOutAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private AccountStatus accountStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "User_Role")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonBackReference
    private Person person;
}
