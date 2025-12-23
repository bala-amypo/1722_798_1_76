// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.ConflictCase;

// public interface ConflictCaseService {
//     List<ConflictCase> getCasesByPerson(Long personId);
//     List<ConflictCase> getAllCases();
//     ConflictCase updateCaseStatus(Long caseId, String status);
//     ConflictCase createCase(ConflictCase conflictCase);
//     ConflictCase getCaseById(Long id);
// }



// ConflictCaseService.java
package com.example.demo.service;

import com.example.demo.model.ConflictCase;
import java.util.List;
import java.util.Optional;

public interface ConflictCaseService {
    ConflictCase createCase(ConflictCase conflictCase);
    ConflictCase updateCaseStatus(Long id, String status);
    List<ConflictCase> getAllCases();
    Optional<ConflictCase> getCaseById(Long id);
    List<ConflictCase> getCasesByPerson(Long personId);
}