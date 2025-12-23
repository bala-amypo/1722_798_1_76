package com.example.demo.controller;

import com.example.demo.model.ConflictCase;
import com.example.demo.service.ConflictCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conflict-cases")
@Tag(name = "Conflict Case", description = "Conflict Case Management")
public class ConflictCaseController {
    
    private final ConflictCaseService caseService;
    
    public ConflictCaseController(ConflictCaseService caseService) {
        this.caseService = caseService;
    }
    
    @PostMapping
    @Operation(summary = "Create conflict case")
    public ResponseEntity<ConflictCase> createCase(@RequestBody ConflictCase conflictCase) {
        ConflictCase saved = caseService.createCase(conflictCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update case status")
    public ResponseEntity<ConflictCase> updateStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        ConflictCase updated = caseService.updateCaseStatus(id, status);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/person/{personId}")
    @Operation(summary = "Get cases linked to person")
    public ResponseEntity<List<ConflictCase>> getByPerson(@PathVariable Long personId) {
        List<ConflictCase> cases = caseService.getCasesByPerson(personId);
        return ResponseEntity.ok(cases);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get conflict case by ID")
    public ResponseEntity<ConflictCase> getById(@PathVariable Long id) {
        Optional<ConflictCase> conflictCase = caseService.getCaseById(id);
        return conflictCase.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "List all cases")
    public ResponseEntity<List<ConflictCase>> getAll() {
        List<ConflictCase> cases = caseService.getAllCases();
        return ResponseEntity.ok(cases);
    }
}