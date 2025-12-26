package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person Profile", description = "Person Profile Management APIs")
@SecurityRequirement(name = "bearerAuth")  // This adds the lock icon to all endpoints
public class PersonProfileController {
    
    private final PersonProfileService personService;
    
    public PersonProfileController(PersonProfileService personService) {
        this.personService = personService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new person profile", description = "Creates a new person profile with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Person created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Duplicate email or reference ID")
    })
    public ResponseEntity<PersonProfile> create(@RequestBody PersonProfile person) {
        PersonProfile created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID", description = "Retrieves a person profile by their ID")
    public ResponseEntity<PersonProfile> getById(
            @Parameter(description = "ID of the person to retrieve", required = true)
            @PathVariable Long id) {
        PersonProfile person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }
    
    @GetMapping
    @Operation(summary = "List all persons", description = "Retrieves a list of all person profiles")
    public ResponseEntity<List<PersonProfile>> getAll() {
        List<PersonProfile> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }
    
    @PutMapping("/{id}/relationship-declared")
    @Operation(summary = "Update relationship-declared flag", description = "Updates the relationship declared status for a person")
    public ResponseEntity<PersonProfile> updateRelationshipDeclared(
            @Parameter(description = "ID of the person to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "New relationship declared status", required = true)
            @RequestParam boolean declared) {
        PersonProfile updated = personService.updateRelationshipDeclared(id, declared);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/lookup/{referenceId}")
    @Operation(summary = "Get person by reference ID", description = "Retrieves a person profile by their reference ID")
    public ResponseEntity<PersonProfile> lookup(
            @Parameter(description = "Reference ID of the person to retrieve", required = true)
            @PathVariable String referenceId) {
        Optional<PersonProfile> person = personService.findByReferenceId(referenceId);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
















// package com.example.demo.controller;

// import com.example.demo.model.PersonProfile;
// import com.example.demo.service.PersonProfileService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/persons")
// @Tag(name = "Person Profile", description = "Person Profile Management")
// public class PersonProfileController {
    
//     private final PersonProfileService personService;
    
//     public PersonProfileController(PersonProfileService personService) {
//         this.personService = personService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Create a new person profile")
//     public ResponseEntity<PersonProfile> create(@RequestBody PersonProfile person) {
//         PersonProfile created = personService.createPerson(person);
//         return ResponseEntity.status(HttpStatus.CREATED).body(created);
//     }
    
//     @GetMapping("/{id}")
//     @Operation(summary = "Get person by ID")
//     public ResponseEntity<PersonProfile> getById(@PathVariable Long id) {
//         PersonProfile person = personService.getPersonById(id);
//         return ResponseEntity.ok(person);
//     }
    
//     @GetMapping
//     @Operation(summary = "List all persons")
//     public ResponseEntity<List<PersonProfile>> getAll() {
//         List<PersonProfile> persons = personService.getAllPersons();
//         return ResponseEntity.ok(persons);
//     }
    
//     @PutMapping("/{id}/relationship-declared")
//     @Operation(summary = "Update relationship-declared flag")
//     public ResponseEntity<PersonProfile> updateRelationshipDeclared(@PathVariable Long id, 
//                                                                    @RequestParam boolean declared) {
//         PersonProfile updated = personService.updateRelationshipDeclared(id, declared);
//         return ResponseEntity.ok(updated);
//     }
    
//     @GetMapping("/lookup/{referenceId}")
//     @Operation(summary = "Get person by reference ID")
//     public ResponseEntity<PersonProfile> lookup(@PathVariable String referenceId) {
//         Optional<PersonProfile> person = personService.findByReferenceId(referenceId);
//         return person.map(ResponseEntity::ok)
//                 .orElseGet(() -> ResponseEntity.notFound().build());
//     }
// }