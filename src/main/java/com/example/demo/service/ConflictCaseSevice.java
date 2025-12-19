package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ConflictCase;

public interface ConflictCaseService {

    List<ConflictCase> getCasesByPerson(Long personId);
    List<ConflictCase> getAllCases();
    ConflictCase updateCaseStatus(Long caseId, String status);
    ConflictCase createCase(ConflictCase conflictCase);
    ConflictCase getCaseById(Long id);

    
    
}
