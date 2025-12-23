package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person Profile", description = "Person Profile Management")
public class PersonProfileController {
    
    private final PersonProfileService personService;
    
    public PersonProfileController(PersonProfileService personService) {
        this.personService = personService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new person profile")
    public ResponseEntity<PersonProfile> create(@RequestBody PersonProfile person) {
        PersonProfile created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID")
    public ResponseEntity<PersonProfile> getById(@PathVariable Long id) {
        PersonProfile person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }
    
    @GetMapping
    @Operation(summary = "List all persons")
    public ResponseEntity<List<PersonProfile>> getAll() {
        List<PersonProfile> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }
    
    @PutMapping("/{id}/relationship-declared")
    @Operation(summary = "Update relationship-declared flag")
    public ResponseEntity<PersonProfile> updateRelationshipDeclared(@PathVariable Long id, 
                                                                   @RequestParam boolean declared) {
        PersonProfile updated = personService.updateRelationshipDeclared(id, declared);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/lookup/{referenceId}")
    @Operation(summary = "Get person by reference ID")
    public ResponseEntity<PersonProfile> lookup(@PathVariable String referenceId) {
        Optional<PersonProfile> person = personService.findByReferenceId(referenceId);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}