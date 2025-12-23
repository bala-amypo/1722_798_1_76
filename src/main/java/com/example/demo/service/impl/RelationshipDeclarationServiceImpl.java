// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.RelationshipDeclaration;
// import com.example.demo.repository.RelationshipDeclarationRepository;
// import com.example.demo.service.RelationshipDeclarationService;
// import java.util.List;

// @Service
// public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
//     private final RelationshipDeclarationRepository relationshipDeclarationRepository;
    
//     public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipDeclarationRepository) {
//         this.relationshipDeclarationRepository = relationshipDeclarationRepository;
//     }
    
//     @Override
//     public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
//         return relationshipDeclarationRepository.save(declaration);
//     }
    
//     @Override
//     public RelationshipDeclaration verify(Long id, boolean verified) {
//         RelationshipDeclaration declaration = relationshipDeclarationRepository.findById(id).orElse(null);
//         if (declaration != null) {
//             declaration.setVerified(verified);
//             return relationshipDeclarationRepository.save(declaration);
//         }
//         return null;
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
//         return relationshipDeclarationRepository.findByPersonId(personId);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getAllDeclarations() {
//         return relationshipDeclarationRepository.findAll();
//     }
// }


// RelationshipDeclarationServiceImpl.java
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
    
    private final RelationshipDeclarationRepository relationshipDeclarationRepository;
    private final PersonProfileRepository personProfileRepository;
    
    public RelationshipDeclarationServiceImpl(
            RelationshipDeclarationRepository relationshipDeclarationRepository,
            PersonProfileRepository personProfileRepository) {
        this.relationshipDeclarationRepository = relationshipDeclarationRepository;
        this.personProfileRepository = personProfileRepository;
    }
    
    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
        // Check if person exists
        PersonProfile person = personProfileRepository.findById(declaration.getPersonId())
            .orElseThrow(() -> new ApiException("Person not found with id: " + declaration.getPersonId()));
        
        // Update person's relationship declared flag
        person.setRelationshipDeclared(true);
        personProfileRepository.save(person);
        
        return relationshipDeclarationRepository.save(declaration);
    }
    
    @Override
    public RelationshipDeclaration verifyDeclaration(Long id, Boolean isVerified) {
        RelationshipDeclaration declaration = relationshipDeclarationRepository.findById(id)
            .orElseThrow(() -> new ApiException("Relationship declaration not found with id: " + id));
        
        declaration.setIsVerified(isVerified);
        return relationshipDeclarationRepository.save(declaration);
    }
    
    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return relationshipDeclarationRepository.findAll();
    }
}