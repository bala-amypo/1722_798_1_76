// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.ConflictFlag;
// import com.example.demo.repository.ConflictFlagRepository;
// import com.example.demo.service.ConflictFlagService;
// import java.util.List;

// @Service
// public class ConflictFlagServiceImpl implements ConflictFlagService {
    
//     private final ConflictFlagRepository conflictFlagRepository;
    
//     public ConflictFlagServiceImpl(ConflictFlagRepository conflictFlagRepository) {
//         this.conflictFlagRepository = conflictFlagRepository;
//     }
    
//     @Override
//     public ConflictFlag addFlag(ConflictFlag flag) {
//         return conflictFlagRepository.save(flag);
//     }
    
//     @Override
//     public List<ConflictFlag> getFlagsByCase(Long caseId) {
//         return conflictFlagRepository.findByCaseId(caseId);
//     }
    
//     @Override
//     public List<ConflictFlag> getFlagById(Long id) {
//         return conflictFlagRepository.findById(id)
//             .map(flag -> List.of(flag))
//             .orElse(List.of());
//     }
    
//     @Override
//     public List<ConflictFlag> GetAllFlags() {
//         return conflictFlagRepository.findAll();
//     }
// }


// ConflictFlagServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConflictFlagServiceImpl implements ConflictFlagService {
    
    private final ConflictFlagRepository conflictFlagRepository;
    private final ConflictCaseRepository conflictCaseRepository;
    
    public ConflictFlagServiceImpl(
            ConflictFlagRepository conflictFlagRepository,
            ConflictCaseRepository conflictCaseRepository) {
        this.conflictFlagRepository = conflictFlagRepository;
        this.conflictCaseRepository = conflictCaseRepository;
    }
    
    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {
        // Check if case exists
        ConflictCase conflictCase = conflictCaseRepository.findById(flag.getCaseId())
            .orElseThrow(() -> new ApiException("Conflict case not found with id: " + flag.getCaseId()));
        
        ConflictFlag savedFlag = conflictFlagRepository.save(flag);
        
        // Update case risk level based on flag severity
        updateCaseRiskLevel(conflictCase);
        
        return savedFlag;
    }
    
    @Override
    public ConflictFlag getFlagById(Long id) {
        return conflictFlagRepository.findById(id)
            .orElseThrow(() -> new ApiException("Conflict flag not found with id: " + id));
    }
    
    @Override
    public List<ConflictFlag> getAllFlags() {
        return conflictFlagRepository.findAll();
    }
    
    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return conflictFlagRepository.findByCaseId(caseId);
    }
    
    private void updateCaseRiskLevel(ConflictCase conflictCase) {
        List<ConflictFlag> flags = conflictFlagRepository.findByCaseId(conflictCase.getId());
        String highestSeverity = "LOW";
        
        for (ConflictFlag flag : flags) {
            switch (flag.getSeverity().toUpperCase()) {
                case "CRITICAL":
                    highestSeverity = "CRITICAL";
                    break;
                case "HIGH":
                    if (!"CRITICAL".equals(highestSeverity)) {
                        highestSeverity = "HIGH";
                    }
                    break;
                case "MEDIUM":
                    if (!"CRITICAL".equals(highestSeverity) && !"HIGH".equals(highestSeverity)) {
                        highestSeverity = "MEDIUM";
                    }
                    break;
            }
        }
        
        conflictCase.setRiskLevel(highestSeverity);
        conflictCaseRepository.save(conflictCase);
    }
}