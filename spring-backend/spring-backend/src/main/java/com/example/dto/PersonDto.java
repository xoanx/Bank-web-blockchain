package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private UUID idPerson;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String taxIdentificationNumber;
    private LocalDateTime createdAt;
    private LocalDateTime  updatedAt;
    private LocalDateTime  expiredAt;
    private List<AccountDto> accounts;
    private List<ContractDto> contracts;
}
