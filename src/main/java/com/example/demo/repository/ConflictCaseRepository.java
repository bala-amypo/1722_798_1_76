package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ConflictCase;
import java.util.List;


public interface ConflictCaseRepository extends JpaRepository<ConflictCase,Long> {
    List<ConflictCase>findByPrimaryPersonIdOrSecondaryPersonId(Long primaryPersonId,Long secondaryPersonId);

    
}
