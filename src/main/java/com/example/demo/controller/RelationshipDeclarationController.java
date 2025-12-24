// package com.example.demo.controller;

// import com.example.demo.model.RelationshipDeclaration;
// import com.example.demo.service.RelationshipDeclarationService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/relationships")
// @Tag(name = "Relationship Declaration", description = "Relationship Declaration Management")
// public class RelationshipDeclarationController {
    
//     private final RelationshipDeclarationService relationshipService;
    
//     public RelationshipDeclarationController(RelationshipDeclarationService relationshipService) {
//         this.relationshipService = relationshipService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Declare a relationship")
//     public ResponseEntity<RelationshipDeclaration> declare(@RequestBody RelationshipDeclaration declaration) {
//         RelationshipDeclaration saved = relationshipService.declareRelationship(declaration);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }
    
//     @GetMapping("/person/{personId}")
//     @Operation(summary = "Get declarations for a person")
//     public ResponseEntity<List<RelationshipDeclaration>> getByPerson(@PathVariable Long personId) {
//         List<RelationshipDeclaration> declarations = relationshipService.getDeclarationsByPerson(personId);
//         return ResponseEntity.ok(declarations);
//     }
    
//     @PutMapping("/{id}/verify")
//     @Operation(summary = "Verify or reject declaration")
//     public ResponseEntity<RelationshipDeclaration> verify(@PathVariable Long id,
//                                                          @RequestParam boolean verified) {
//         RelationshipDeclaration updated = relationshipService.verifyDeclaration(id, verified);
//         return ResponseEntity.ok(updated);
//     }
    
//     @GetMapping
//     @Operation(summary = "List all relationship declarations")
//     public ResponseEntity<List<RelationshipDeclaration>> getAll() {
//         List<RelationshipDeclaration> declarations = relationshipService.getAllDeclarations();
//         return ResponseEntity.ok(declarations);
//     }
// }



// package com.example.demo.controller;

// import com.example.demo.model.RelationshipDeclaration;
// import com.example.demo.service.RelationshipDeclarationService;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/relationships")
// public class RelationshipDeclarationController {
    
//     private final RelationshipDeclarationService relationshipService;
    
//     public RelationshipDeclarationController(RelationshipDeclarationService relationshipService) {
//         this.relationshipService = relationshipService;
//     }
    
//     @PostMapping
//     public ResponseEntity<RelationshipDeclaration> declare(@RequestBody RelationshipDeclaration declaration) {
//         RelationshipDeclaration saved = relationshipService.declareRelationship(declaration);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }
    
//     @GetMapping("/person/{personId}")
//     public ResponseEntity<List<RelationshipDeclaration>> getByPerson(@PathVariable Long personId) {
//         List<RelationshipDeclaration> declarations = relationshipService.getDeclarationsByPerson(personId);
//         return ResponseEntity.ok(declarations);
//     }
    
//     @PutMapping("/{id}/verify")
//     public ResponseEntity<RelationshipDeclaration> verify(@PathVariable Long id,
//                                                          @RequestParam boolean verified) {
//         RelationshipDeclaration updated = relationshipService.verifyDeclaration(id, verified);
//         return ResponseEntity.ok(updated);
//     }
    
//     @GetMapping
//     public ResponseEntity<List<RelationshipDeclaration>> getAll() {
//         List<RelationshipDeclaration> declarations = relationshipService.getAllDeclarations();
//         return ResponseEntity.ok(declarations);
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.service.RelationshipDeclarationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
public class RelationshipDeclarationController {
    
    private final RelationshipDeclarationService relationshipService;
    
    public RelationshipDeclarationController(RelationshipDeclarationService relationshipService) {
        this.relationshipService = relationshipService;
    }
    
    @PostMapping
    public ResponseEntity<RelationshipDeclaration> declareRelationship(@RequestBody RelationshipDeclaration declaration) {
        try {
            RelationshipDeclaration saved = relationshipService.declareRelationship(declaration);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<RelationshipDeclaration>> getDeclarationsByPerson(@PathVariable Long personId) {
        List<RelationshipDeclaration> declarations = relationshipService.getDeclarationsByPerson(personId);
        return ResponseEntity.ok(declarations);
    }
    
    @PutMapping("/{id}/verify")
    public ResponseEntity<RelationshipDeclaration> verifyDeclaration(@PathVariable Long id,
                                                                    @RequestParam boolean verified) {
        try {
            RelationshipDeclaration updated = relationshipService.verifyDeclaration(id, verified);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<RelationshipDeclaration>> getAllDeclarations() {
        List<RelationshipDeclaration> declarations = relationshipService.getAllDeclarations();
        return ResponseEntity.ok(declarations);
    }
}