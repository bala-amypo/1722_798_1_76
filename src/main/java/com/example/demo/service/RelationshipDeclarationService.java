package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RelationshipDeclaration;

public interface RelationshipDeclarationService {


    RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration);
    RelationshipDeclaration verify(Long id, boolean verified);
    
    List<RelationshipDeclaration> getDeclarationsByPerson(Long personId);
    List<RelationshipDeclaration> getAllDeclarations();
}
