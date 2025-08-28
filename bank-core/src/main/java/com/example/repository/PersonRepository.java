package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    boolean existsByEmail(String email);

    Optional<Person> findByEmail(String email);

    Optional<Person> findByPhoneNumber(String phoneNumber);

    Optional<Person> findByTaxIdentificationNumber(String taxIdNumber);

    List<Person> findByLastName(String lastName);
}
