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