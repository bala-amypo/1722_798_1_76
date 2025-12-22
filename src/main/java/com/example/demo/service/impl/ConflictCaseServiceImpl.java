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



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.entity.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictCaseService;

import java.util.List;
import java.util.Optional;

public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final ConflictCaseRepository repo;
    private final ConflictFlagRepository flagRepo;

    public ConflictCaseServiceImpl(
            ConflictCaseRepository repo,
            ConflictFlagRepository flagRepo) {
        this.repo = repo;
        this.flagRepo = flagRepo;
    }

    @Override
    public ConflictCase createCase(ConflictCase c) {
        if (c.getStatus() == null) {
            c.setStatus("OPEN");
        }
        return repo.save(c);
    }

    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        ConflictCase c = repo.findById(id)
                .orElseThrow(() -> new ApiException("case not found"));
        c.setStatus(status);
        return repo.save(c);
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return repo.findAll();
    }

    @Override
    public Optional<ConflictCase> getCaseById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<ConflictCase> getCasesByPerson(Long id) {
        return repo.findByPrimaryPersonIdOrSecondaryPersonId(id, id);
    }
}
