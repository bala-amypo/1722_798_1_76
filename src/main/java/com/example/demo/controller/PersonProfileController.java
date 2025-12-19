package com.example.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Personprofile;
import com.example.demo.service.PersonProfileService;

@RestController
@RequestMapping("/api/persons")
public class PersonProfileController {

    private PersonProfileService service;

    public PersonProfileController(PersonProfileService service) {
        this.service = service;
    }
    @PostMapping
    public Personprofile createPerson(@RequestBody Personprofile person){
        return service.createPerson(person);
    }

    @GetMapping("/{id}")
    public Personprofile getById(@PathVariable Long id){
        return service.getPersonById(id); 

    }

    @GetMapping
    public List<Personprofile>getAll(){
        return service.getAllPerson();
    }

    @PutMapping("/{id}/relationship-declared")
    public Personprofile update(@PathVariable Long id, @RequestParam boolean declared){
        return service.updateRelationshipDeclared(id,declared);
    }

    @GetMapping("/lookup/{referencedId}")
    public Personprofile findById(@PathVariable String referenceId){
        return service.findByReferenceId(referenceId);
    }

    
}
