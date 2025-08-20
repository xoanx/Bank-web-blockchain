package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Person")
    private UUID idPerson;

    @Column(name = "First_Name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "Last_Name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Birthdate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "Phone_Number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name = "Address", length = 100, nullable = false)
    private String address;

    @Column(name = "Tax_Number", length = 50, nullable = false, unique = true)
    private String taxIdentificationNumber;

    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_At", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "Expired_At")
    private LocalDateTime expiredAt;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> accounts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Contract> contracts;
}
