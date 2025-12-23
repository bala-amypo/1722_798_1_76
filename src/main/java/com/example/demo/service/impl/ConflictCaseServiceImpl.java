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