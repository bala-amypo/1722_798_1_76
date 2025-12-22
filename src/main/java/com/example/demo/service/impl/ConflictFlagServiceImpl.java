package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.ConflictFlag;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;
import java.util.List;

@Service
public class ConflictFlagServiceImpl implements ConflictFlagService {
    
    private final ConflictFlagRepository conflictFlagRepository;
    
    public ConflictFlagServiceImpl(ConflictFlagRepository conflictFlagRepository) {
        this.conflictFlagRepository = conflictFlagRepository;
    }
    
    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {
        return conflictFlagRepository.save(flag);
    }
    
    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return conflictFlagRepository.findByCaseId(caseId);
    }
    
    @Override
    public List<ConflictFlag> getFlagById(Long id) {
        return conflictFlagRepository.findById(id)
            .map(flag -> List.of(flag))
            .orElse(List.of());
    }
    
    @Override
    public List<ConflictFlag> GetAllFlags() {
        return conflictFlagRepository.findAll();
    }
}