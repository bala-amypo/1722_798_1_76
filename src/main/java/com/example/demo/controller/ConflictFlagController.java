package com.example.demo.controller;

import com.example.demo.model.ConflictFlag;
import com.example.demo.service.ConflictFlagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conflict-flags")
public class ConflictFlagController {
    
    private final ConflictFlagService flagService;
    
    public ConflictFlagController(ConflictFlagService flagService) {
        this.flagService = flagService;
    }
    
    @PostMapping
    public ResponseEntity<ConflictFlag> addFlag(@RequestBody ConflictFlag flag) {
        ConflictFlag saved = flagService.addFlag(flag);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<ConflictFlag>> getByCase(@PathVariable Long caseId) {
        List<ConflictFlag> flags = flagService.getFlagsByCase(caseId);
        return ResponseEntity.ok(flags);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConflictFlag> getById(@PathVariable Long id) {
        ConflictFlag flag = flagService.getFlagById(id);
        return ResponseEntity.ok(flag);
    }
    
    @GetMapping
    public ResponseEntity<List<ConflictFlag>> getAll() {
        List<ConflictFlag> flags = flagService.getAllFlags();
        return ResponseEntity.ok(flags);
    }
}








// package com.example.demo.controller;

// import com.example.demo.model.ConflictFlag;
// import com.example.demo.service.ConflictFlagService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/conflict-flags")
// @Tag(name = "Conflict Flag", description = "Conflict Flag Management")
// public class ConflictFlagController {
    
//     private final ConflictFlagService flagService;
    
//     public ConflictFlagController(ConflictFlagService flagService) {
//         this.flagService = flagService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Add conflict flag")
//     public ResponseEntity<ConflictFlag> addFlag(@RequestBody ConflictFlag flag) {
//         ConflictFlag saved = flagService.addFlag(flag);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }
    
//     @GetMapping("/case/{caseId}")
//     @Operation(summary = "Get all flags for a conflict case")
//     public ResponseEntity<List<ConflictFlag>> getByCase(@PathVariable Long caseId) {
//         List<ConflictFlag> flags = flagService.getFlagsByCase(caseId);
//         return ResponseEntity.ok(flags);
//     }
    
//     @GetMapping("/{id}")
//     @Operation(summary = "Get conflict flag by ID")
//     public ResponseEntity<ConflictFlag> getById(@PathVariable Long id) {
//         ConflictFlag flag = flagService.getFlagById(id);
//         return ResponseEntity.ok(flag);
//     }
    
//     @GetMapping
//     @Operation(summary = "List all conflict flags")
//     public ResponseEntity<List<ConflictFlag>> getAll() {
//         List<ConflictFlag> flags = flagService.getAllFlags();
//         return ResponseEntity.ok(flags);
//     }
// }