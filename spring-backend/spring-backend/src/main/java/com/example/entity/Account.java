package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jshell.Snippet;
import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
    private Status AccountStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "User_Role")
    private UserRole role;
    public enum UserRole {
        ADMIN, USER, CLIENT, BANK
    }
    public enum Status {
        ACTIVE, INACTIVE, SUSPENDED
    }

    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonBackReference
    private Person person;
}
