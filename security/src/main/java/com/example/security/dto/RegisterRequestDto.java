package com.example.security.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    // Account
    private String username;
    private String password;
    private String currency;
    // Person
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String taxIdentificationNumber;
}
