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
        try {
            ConflictFlag saved = flagService.addFlag(flag);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<ConflictFlag>> getFlagsByCase(@PathVariable Long caseId) {
        List<ConflictFlag> flags = flagService.getFlagsByCase(caseId);
        return ResponseEntity.ok(flags);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConflictFlag> getFlagById(@PathVariable Long id) {
        try {
            ConflictFlag flag = flagService.getFlagById(id);
            return ResponseEntity.ok(flag);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ConflictFlag>> getAllFlags() {
        List<ConflictFlag> flags = flagService.getAllFlags();
        return ResponseEntity.ok(flags);
    }
}











































// package com.example.demo.controller;

// import com.example.demo.model.ConflictFlag;
// import com.example.demo.service.ConflictFlagService;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/conflict-flags")
// public class ConflictFlagController {
    
//     private final ConflictFlagService flagService;
    
//     public ConflictFlagController(ConflictFlagService flagService) {
//         this.flagService = flagService;
//     }
    
//     @PostMapping
//     public ResponseEntity<ConflictFlag> addFlag(@RequestBody ConflictFlag flag) {
//         ConflictFlag saved = flagService.addFlag(flag);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }
    
//     @GetMapping("/case/{caseId}")
//     public ResponseEntity<List<ConflictFlag>> getByCase(@PathVariable Long caseId) {
//         List<ConflictFlag> flags = flagService.getFlagsByCase(caseId);
//         return ResponseEntity.ok(flags);
//     }
    
//     @GetMapping("/{id}")
//     public ResponseEntity<ConflictFlag> getById(@PathVariable Long id) {
//         ConflictFlag flag = flagService.getFlagById(id);
//         return ResponseEntity.ok(flag);
//     }
    
//     @GetMapping
//     public ResponseEntity<List<ConflictFlag>> getAll() {
//         List<ConflictFlag> flags = flagService.getAllFlags();
//         return ResponseEntity.ok(flags);
//     }
// }



