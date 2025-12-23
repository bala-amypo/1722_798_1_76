package com.example.demo.repository;

import com.example.demo.model.ConflictFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictFlagRepository extends JpaRepository<ConflictFlag, Long> {
    List<ConflictFlag> findByCaseId(Long id);
}