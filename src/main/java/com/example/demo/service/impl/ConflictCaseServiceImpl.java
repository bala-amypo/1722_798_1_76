// // package com.example.demo.service.impl;

// // import com.example.demo.exception.ApiException;
// // import com.example.demo.model.ConflictCase;
// // import com.example.demo.model.ConflictFlag;
// // import com.example.demo.repository.ConflictCaseRepository;
// // import com.example.demo.repository.ConflictFlagRepository;
// // import com.example.demo.service.ConflictCaseService;
// // import org.springframework.stereotype.Service;

// // import java.util.List;
// // import java.util.Optional;

// // @Service
// // public class ConflictCaseServiceImpl implements ConflictCaseService {
    
// //     private final ConflictCaseRepository caseRepository;
// //     private final ConflictFlagRepository flagRepository;
    
// //     public ConflictCaseServiceImpl(ConflictCaseRepository caseRepository,
// //                                   ConflictFlagRepository flagRepository) {
// //         this.caseRepository = caseRepository;
// //         this.flagRepository = flagRepository;
// //     }
    
// //     @Override
// //     public ConflictCase createCase(ConflictCase conflictCase) {
// //         // Set default status if null
// //         if (conflictCase.getStatus() == null || conflictCase.getStatus().trim().isEmpty()) {
// //             conflictCase.setStatus("OPEN");
// //         }
        
// //         return caseRepository.save(conflictCase);
// //     }
    
// //     @Override
// //     public ConflictCase updateCaseStatus(Long caseId, String status) {
// //         ConflictCase conflictCase = caseRepository.findById(caseId)
// //                 .orElseThrow(() -> new ApiException("Conflict case not found"));
        
// //         conflictCase.setStatus(status);
// //         return caseRepository.save(conflictCase);
// //     }
    
// //     @Override
// //     public List<ConflictCase> getCasesByPerson(Long personId) {
// //         return caseRepository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
// //     }
    
// //     @Override
// //     public Optional<ConflictCase> getCaseById(Long id) {
// //         return caseRepository.findById(id);
// //     }
    
// //     @Override
// //     public List<ConflictCase> getAllCases() {
// //         return caseRepository.findAll();
// //     }
// // }



// package com.example.demo.service.impl;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.ConflictCase;
// import com.example.demo.model.ConflictFlag;
// import com.example.demo.repository.ConflictCaseRepository;
// import com.example.demo.repository.ConflictFlagRepository;
// import com.example.demo.service.ConflictFlagService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ConflictFlagServiceImpl implements ConflictFlagService {
    
//     private final ConflictFlagRepository flagRepository;
//     private final ConflictCaseRepository caseRepository;
    
//     public ConflictFlagServiceImpl(ConflictFlagRepository flagRepository,
//                                   ConflictCaseRepository caseRepository) {
//         this.flagRepository = flagRepository;
//         this.caseRepository = caseRepository;
//     }
    
//     @Override
//     public ConflictFlag addFlag(ConflictFlag flag) {
//         // Check if case exists
//         if (flag.getCaseId() == null) {
//             throw new ApiException("Case ID is required");
//         }
        
//         ConflictCase conflictCase = caseRepository.findById(flag.getCaseId())
//                 .orElseThrow(() -> new ApiException("Conflict case not found"));
        
//         // Update case risk level based on flag severity if severity is provided
//         if (flag.getSeverity() != null && !flag.getSeverity().trim().isEmpty()) {
//             conflictCase.setRiskLevel(flag.getSeverity());
//             caseRepository.save(conflictCase);
//         }
        
//         return flagRepository.save(flag);
//     }
    
//     @Override
//     public List<ConflictFlag> getFlagsByCase(Long caseId) {
//         return flagRepository.findByCaseId(caseId);
//     }
    
//     @Override
//     public ConflictFlag getFlagById(Long id) {
//         return flagRepository.findById(id)
//                 .orElseThrow(() -> new ApiException("Conflict flag not found"));
//     }
    
//     @Override
//     public List<ConflictFlag> getAllFlags() {
//         return flagRepository.findAll();
//     }
// }



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
    
    private final ConflictCaseRepository caseRepository;
    private final ConflictFlagRepository flagRepository;
    
    public ConflictCaseServiceImpl(ConflictCaseRepository caseRepository,
                                  ConflictFlagRepository flagRepository) {
        this.caseRepository = caseRepository;
        this.flagRepository = flagRepository;
    }
    
    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        // Set default status if null
        if (conflictCase.getStatus() == null || conflictCase.getStatus().trim().isEmpty()) {
            conflictCase.setStatus("OPEN");
        }
        
        return caseRepository.save(conflictCase);
    }
    
    @Override
    public ConflictCase updateCaseStatus(Long caseId, String status) {
        ConflictCase conflictCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new ApiException("Conflict case not found"));
        
        conflictCase.setStatus(status);
        return caseRepository.save(conflictCase);
    }
    
    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return caseRepository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
    }
    
    @Override
    public Optional<ConflictCase> getCaseById(Long id) {
        return caseRepository.findById(id);
    }
    
    @Override
    public List<ConflictCase> getAllCases() {
        return caseRepository.findAll();
    }
}