package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
    private final RelationshipDeclarationRepository relationshipRepository;
    private final PersonProfileRepository personRepository;
    
    public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipRepository,
                                             PersonProfileRepository personRepository) {
        this.relationshipRepository = relationshipRepository;
        this.personRepository = personRepository;
    }
    
    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
        // Check if person exists
        PersonProfile person = personRepository.findById(declaration.getPersonId())
                .orElseThrow(() -> new ApiException("Person not found"));
        
        // Update person's relationshipDeclared flag
        if (!person.getRelationshipDeclared()) {
            person.setRelationshipDeclared(true);
            personRepository.save(person);
        }
        
        // Set default isVerified if null
        if (declaration.getIsVerified() == null) {
            declaration.setIsVerified(false);
        }
        
        return relationshipRepository.save(declaration);
    }
    
    @Override
    public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
        return relationshipRepository.findByPersonId(personId);
    }
    
    @Override
    public RelationshipDeclaration verifyDeclaration(Long declarationId, boolean verified) {
        RelationshipDeclaration declaration = relationshipRepository.findById(declarationId)
                .orElseThrow(() -> new ApiException("Declaration not found"));
        
        declaration.setIsVerified(verified);
        return relationshipRepository.save(declaration);
    }
    
    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return relationshipRepository.findAll();
    }
}