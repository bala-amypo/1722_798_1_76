package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.service.ConflictCaseService;
import java.util.List;

@Service
public class ConflictCaseServiceImpl implements ConflictCaseService {
    
    private final ConflictCaseRepository conflictCaseRepository;
    
    public ConflictCaseServiceImpl(ConflictCaseRepository conflictCaseRepository) {
        this.conflictCaseRepository = conflictCaseRepository;
    }
    
    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        return conflictCaseRepository.save(conflictCase);
    }
    
    @Override
    public ConflictCase updateCaseStatus(Long caseId, String status) {
        ConflictCase conflictCase = conflictCaseRepository.findById(caseId).orElse(null);
        if (conflictCase != null) {
            conflictCase.setStatus(status);
            return conflictCaseRepository.save(conflictCase);
        }
        return null;
    }
    
    @Override
    public ConflictCase getCaseById(Long id) {
        return conflictCaseRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return conflictCaseRepository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
    }
    
    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }
}