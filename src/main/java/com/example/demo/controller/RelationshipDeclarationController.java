package com.example.demo.controller;

import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.service.RelationshipDeclarationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
@Tag(name = "Relationship Declaration", description = "Relationship Declaration Management")
public class RelationshipDeclarationController {
    
    private final RelationshipDeclarationService relationshipService;
    
    public RelationshipDeclarationController(RelationshipDeclarationService relationshipService) {
        this.relationshipService = relationshipService;
    }
    
    @PostMapping
    @Operation(summary = "Declare a relationship")
    public ResponseEntity<RelationshipDeclaration> declare(@RequestBody RelationshipDeclaration declaration) {
        RelationshipDeclaration saved = relationshipService.declareRelationship(declaration);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/person/{personId}")
    @Operation(summary = "Get declarations for a person")
    public ResponseEntity<List<RelationshipDeclaration>> getByPerson(@PathVariable Long personId) {
        List<RelationshipDeclaration> declarations = relationshipService.getDeclarationsByPerson(personId);
        return ResponseEntity.ok(declarations);
    }
    
    @PutMapping("/{id}/verify")
    @Operation(summary = "Verify or reject declaration")
    public ResponseEntity<RelationshipDeclaration> verify(@PathVariable Long id,
                                                         @RequestParam boolean verified) {
        RelationshipDeclaration updated = relationshipService.verifyDeclaration(id, verified);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping
    @Operation(summary = "List all relationship declarations")
    public ResponseEntity<List<RelationshipDeclaration>> getAll() {
        List<RelationshipDeclaration> declarations = relationshipService.getAllDeclarations();
        return ResponseEntity.ok(declarations);
    }
}