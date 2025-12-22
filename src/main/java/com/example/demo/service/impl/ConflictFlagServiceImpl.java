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



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;

import java.util.List;

public class ConflictFlagServiceImpl implements ConflictFlagService {

    private final ConflictFlagRepository repo;
    private final ConflictCaseRepository caseRepo;

    public ConflictFlagServiceImpl(
            ConflictFlagRepository repo,
            ConflictCaseRepository caseRepo) {
        this.repo = repo;
        this.caseRepo = caseRepo;
    }

    @Override
    public ConflictFlag addFlag(ConflictFlag f) {

        ConflictCase c = caseRepo.findById(f.getCaseId())
                .orElseThrow(() -> new ApiException("case not found"));

        if ("HIGH".equalsIgnoreCase(f.getSeverity())) {
            c.setRiskLevel("HIGH");
            caseRepo.save(c);
        }

        return repo.save(f);
    }

    @Override
    public ConflictFlag getFlagById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("flag not found"));
    }

    @Override
    public List<ConflictFlag> getAllFlags() {
        return repo.findAll();
    }

    @Override
    public List<ConflictFlag> getFlagsByCase(Long id) {
        return repo.findByCaseId(id);
    }
}
