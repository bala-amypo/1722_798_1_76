package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.RelationshipDeclaration;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;
import java.util.List;

@Service
public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
    private final RelationshipDeclarationRepository relationshipDeclarationRepository;
    
    public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipDeclarationRepository) {
        this.relationshipDeclarationRepository = relationshipDeclarationRepository;
    }
    
    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
        return relationshipDeclarationRepository.save(declaration);
    }
    
    @Override
    public RelationshipDeclaration verify(Long id, boolean verified) {
        RelationshipDeclaration declaration = relationshipDeclarationRepository.findById(id).orElse(null);
        if (declaration != null) {
            declaration.setVerified(verified);
            return relationshipDeclarationRepository.save(declaration);
        }
        return null;
    }
    
    @Override
    public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
        return relationshipDeclarationRepository.findByPersonId(personId);
    }
    
    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return relationshipDeclarationRepository.findAll();
    }
}