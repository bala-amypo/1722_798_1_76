// package com.example.demo.controller;

// import java.util.List;


// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.Personprofile;
// import com.example.demo.service.PersonProfileService;

// @RestController
// @RequestMapping("/api/persons")
// public class PersonProfileController {

//     private PersonProfileService service;

//     public PersonProfileController(PersonProfileService service) {
//         this.service = service;
//     }
//     @PostMapping
//     public Personprofile createPerson(@RequestBody Personprofile person){
//         return service.createPerson(person);
//     }

//     @GetMapping("/{id}")
//     public Personprofile getById(@PathVariable Long id){
//         return service.getPersonById(id); 

//     }

//     @GetMapping
//     public List<Personprofile>getAll(){
//         return service.getAllPerson();
//     }

//     @PutMapping("/{id}/relationship-declared")
//     public Personprofile update(@PathVariable Long id, @RequestParam boolean declared){
//         return service.updateRelationshipDeclared(id,declared);
//     }

//     @GetMapping("/lookup/{referencedId}")
//     public Personprofile findById(@PathVariable String referenceId){
//         return service.findByReferenceId(referenceId);
//     }

    
// }
// PersonProfileController.java
package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonProfileController {
    
    private final PersonProfileService personProfileService;
    
    public PersonProfileController(PersonProfileService personProfileService) {
        this.personProfileService = personProfileService;
    }
    
    @PostMapping
    public ResponseEntity<PersonProfile> create(@RequestBody PersonProfile person) {
        PersonProfile created = personProfileService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/lookup")
    public ResponseEntity<PersonProfile> lookup(@RequestParam String referenceId) {
        Optional<PersonProfile> person = personProfileService.findByReferenceId(referenceId);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PersonProfile> getById(@PathVariable Long id) {
        try {
            PersonProfile person = personProfileService.getPersonById(id);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}