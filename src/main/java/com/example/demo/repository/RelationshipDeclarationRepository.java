package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.RelationshipDeclaration;
import java.util.List;

public interface RelationshipDeclarationRepository extends JpaRepository<RelationshipDeclaration, Long> {
    List<RelationshipDeclaration> findByPersonId(Long personId);
}