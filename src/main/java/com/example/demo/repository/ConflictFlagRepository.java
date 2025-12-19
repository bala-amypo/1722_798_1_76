package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ConflictFlag;
import java.util.List;

public interface ConflictFlagRepository extends JpaRepository<ConflictFlag, Long> {
    List<ConflictFlag> findByCaseId(Long caseId);
}