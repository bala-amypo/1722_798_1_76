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
    
    private final ConflictFlagRepository flagRepository;
    private final ConflictCaseRepository caseRepository;
    
    public ConflictFlagServiceImpl(ConflictFlagRepository flagRepository,
                                  ConflictCaseRepository caseRepository) {
        this.flagRepository = flagRepository;
        this.caseRepository = caseRepository;
    }
    
    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {
        // Check if case exists
        ConflictCase conflictCase = caseRepository.findById(flag.getCaseId())
                .orElseThrow(() -> new ApiException("Conflict case not found"));
        
        // Update case risk level based on flag severity
        if (flag.getSeverity() != null && !flag.getSeverity().trim().isEmpty()) {
            conflictCase.setRiskLevel(flag.getSeverity());
            caseRepository.save(conflictCase);
        }
        
        return flagRepository.save(flag);
    }
    
    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return flagRepository.findByCaseId(caseId);
    }
    
    @Override
    public ConflictFlag getFlagById(Long id) {
        return flagRepository.findById(id)
                .orElseThrow(() -> new ApiException("Conflict flag not found"));
    }
    
    @Override
    public List<ConflictFlag> getAllFlags() {
        return flagRepository.findAll();
    }
}