// package com.example.demo.controller;

// import java.util.List;


// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.ConflictFlag;
// import com.example.demo.service.ConflictFlagService;

// @RestController
// @RequestMapping("/api/conflict-flag")
// public class ConflictFlagController {

//     private ConflictFlagService service;

//     public ConflictFlagController(ConflictFlagService service) {
//         this.service = service;
//     }
//     @PostMapping
//     public ConflictFlag add(@RequestBody ConflictFlag flag){
//         return service.addFlag(flag);
//     }
//     @GetMapping("/case/{caseId}")
//     public List<ConflictFlag> getByCase(
//             @PathVariable Long caseId) {
//         return service.getFlagsByCase(caseId);
//     }

//     @GetMapping("/{id}")
//     public List<ConflictFlag> getById(@PathVariable Long id){
//         return service.getFlagById(id);
//     }

//     @GetMapping
//     public List<ConflictFlag>getAll(){
//         return service.GetAllFlags();
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.ConflictFlag;
import com.example.demo.service.ConflictFlagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class ConflictFlagController {
    
    private final ConflictFlagService conflictFlagService;
    
    public ConflictFlagController(ConflictFlagService conflictFlagService) {
        this.conflictFlagService = conflictFlagService;
    }
    
    @PostMapping
    public ResponseEntity<ConflictFlag> addFlag(@RequestBody ConflictFlag flag) {
        try {
            ConflictFlag saved = conflictFlagService.addFlag(flag);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ConflictFlag>> getAllFlags() {
        List<ConflictFlag> flags = conflictFlagService.getAllFlags();
        return ResponseEntity.ok(flags);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConflictFlag> getFlagById(@PathVariable Long id) {
        try {
            ConflictFlag flag = conflictFlagService.getFlagById(id);
            return ResponseEntity.ok(flag);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<ConflictFlag>> getFlagsByCase(@PathVariable Long caseId) {
        List<ConflictFlag> flags = conflictFlagService.getFlagsByCase(caseId);
        return ResponseEntity.ok(flags);
    }
}