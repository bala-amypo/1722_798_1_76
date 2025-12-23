// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.ConflictCase;
// import com.example.demo.repository.ConflictCaseRepository;
// import com.example.demo.service.ConflictCaseService;
// import java.util.List;

// @Service
// public class ConflictCaseServiceImpl implements ConflictCaseService {
    
//     private final ConflictCaseRepository conflictCaseRepository;
    
//     public ConflictCaseServiceImpl(ConflictCaseRepository conflictCaseRepository) {
//         this.conflictCaseRepository = conflictCaseRepository;
//     }
    
//     @Override
//     public ConflictCase createCase(ConflictCase conflictCase) {
//         return conflictCaseRepository.save(conflictCase);
//     }
    
//     @Override
//     public ConflictCase updateCaseStatus(Long caseId, String status) {
//         ConflictCase conflictCase = conflictCaseRepository.findById(caseId).orElse(null);
//         if (conflictCase != null) {
//             conflictCase.setStatus(status);
//             return conflictCaseRepository.save(conflictCase);
//         }
//         return null;
//     }
    
//     @Override
//     public ConflictCase getCaseById(Long id) {
//         return conflictCaseRepository.findById(id).orElse(null);
//     }
    
//     @Override
//     public List<ConflictCase> getCasesByPerson(Long personId) {
//         return conflictCaseRepository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
//     }
    
//     @Override
//     public List<ConflictCase> getAllCases() {
//         return conflictCaseRepository.findAll();
//     }
// }




// ConflictCaseServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConflictCaseServiceImpl implements ConflictCaseService {
    
    private final ConflictCaseRepository conflictCaseRepository;
    private final ConflictFlagRepository conflictFlagRepository;
    
    public ConflictCaseServiceImpl(
            ConflictCaseRepository conflictCaseRepository,
            ConflictFlagRepository conflictFlagRepository) {
        this.conflictCaseRepository = conflictCaseRepository;
        this.conflictFlagRepository = conflictFlagRepository;
    }
    
    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        // Test expects default status to be "OPEN" if null (test55)
        if (conflictCase.getStatus() == null) {
            conflictCase.setStatus("OPEN");
        }
        return conflictCaseRepository.save(conflictCase);
    }
    
    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        // Test expects ApiException when not found (test51)
        ConflictCase conflictCase = conflictCaseRepository.findById(id)
            .orElseThrow(() -> new ApiException("Conflict case not found with id: " + id));
        
        conflictCase.setStatus(status);
        return conflictCaseRepository.save(conflictCase);
    }
    
    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }
    
    @Override
    public Optional<ConflictCase> getCaseById(Long id) {
        return conflictCaseRepository.findById(id);
    }
    
    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return conflictCaseRepository.findByPrimaryPersonIdOrSecondaryPersonId(personId);
    }
}