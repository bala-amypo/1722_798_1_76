package com.example.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RelationshipDeclaration;
import com.example.demo.service.RelationshipDeclarationService;

@RestController
@RequestMapping("/api/relationships")
public class RelationshipDeclarationController {

    private RelationshipDeclarationService service;

    public RelationshipDeclarationController(RelationshipDeclarationService service) {
        this.service = service;
    }
    @PostMapping
    public RelationshipDeclaration declare(@RequestBody RelationshipDeclaration declaration){
        return service.declareRelationship(declaration);
    }
    
    @GetMapping("/person/{personId}")
    public List<RelationshipDeclaration>getByPerson(@PathVariable Long personId){
        return service.getDeclarationsByPerson(personId);
    }
    
    @PutMapping("/{id}/verify")
    public RelationshipDeclaration verify(@PathVariable Long id,@RequestParam boolean verified){
        return service.verify(id,verified);
    }
    
    @GetMapping
    public List<RelationshipDeclaration>getAll(){
        return service.getAllDeclarations();
    }
}
