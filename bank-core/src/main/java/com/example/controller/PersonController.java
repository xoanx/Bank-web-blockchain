package com.example.controller;

import com.example.dto.detail.PersonDetailResponseDto;
import com.example.dto.lite.ContractLiteResponseDto;
import com.example.dto.lite.PersonLiteResponseDto;
import com.example.dto.request.PersonRequestDto;
import com.example.entity.Person;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/create-person")
    public ResponseEntity<PersonDetailResponseDto> createPerson(
            @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.createPerson(personRequestDto));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDetailResponseDto> getPerson(
            @PathVariable UUID personId){
        return ResponseEntity.ok(personService.getPersonById(personId));
    }

    @GetMapping
    public ResponseEntity<List<PersonLiteResponseDto>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDetailResponseDto> updatePerson(
            @PathVariable UUID personId,
            @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.updatedPerson(personId, personRequestDto));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID personId) {
        personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<PersonDetailResponseDto>> searchPerson(@RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.searchPerson(personRequestDto));
    }

    @GetMapping("/{personId}/contracts")
    public ResponseEntity<List<ContractLiteResponseDto>> getContractsOfPerson(@PathVariable UUID personId) {
        return ResponseEntity.ok(personService.getContractsOfPerson(personId));
    }
}
