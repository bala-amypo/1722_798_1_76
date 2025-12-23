// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.ConflictCase;
// import com.example.demo.service.ConflictCaseService;

// @RestController
// @RequestMapping("/api/conflict-cases")
// public class ConflictCaseController {

//     private final ConflictCaseService service;

//     public ConflictCaseController(ConflictCaseService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public ConflictCase createCase(@RequestBody ConflictCase conflictCase) {
//         return service.createCase(conflictCase);
//     }

//     @PutMapping("/{id}/status")
//     public ConflictCase updateStatus(
//             @PathVariable Long id,
//             @RequestParam String status) {
//         return service.updateCaseStatus(id, status);
//     }

//     @GetMapping("/{id}")
//     public ConflictCase getCaseById(@PathVariable Long id) {
//         return service.getCaseById(id);
//     }

//     @GetMapping("/person/{personId}")
//     public List<ConflictCase> getCasesByPerson(
//             @PathVariable Long personId) {
//         return service.getCasesByPerson(personId);
//     }

//     @GetMapping
//     public List<ConflictCase> getAllCases() {
//         return service.getAllCases();
//     }
// }


package com.example.demo.controller;

import com.example.demo.model.ConflictCase;
import com.example.demo.service.ConflictCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cases")
public class ConflictCaseController {
    
    private final ConflictCaseService conflictCaseService;
    
    public ConflictCaseController(ConflictCaseService conflictCaseService) {
        this.conflictCaseService = conflictCaseService;
    }
    
    @PostMapping
    public ResponseEntity<ConflictCase> createCase(@RequestBody ConflictCase conflictCase) {
        try {
            ConflictCase saved = conflictCaseService.createCase(conflictCase);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<ConflictCase> updateStatus(
            @PathVariable Long id, 
            @RequestParam String status) {
        try {
            ConflictCase updated = conflictCaseService.updateCaseStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ConflictCase>> getAllCases() {
        List<ConflictCase> cases = conflictCaseService.getAllCases();
        return ResponseEntity.ok(cases);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConflictCase> getCaseById(@PathVariable Long id) {
        Optional<ConflictCase> conflictCase = conflictCaseService.getCaseById(id);
        return conflictCase.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<ConflictCase>> getCasesByPerson(@PathVariable Long personId) {
        List<ConflictCase> cases = conflictCaseService.getCasesByPerson(personId);
        return ResponseEntity.ok(cases);
    }
}