package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ConflictFlag;

public interface ConflictFlagService {
    ConflictFlag addFlag(ConflictFlag flag);
    List<ConflictFlag> getFlagsByCase(Long caseId);
    List<ConflictFlag> getFlagById(Long id);
    List<ConflictFlag> GetAllFlags();
}