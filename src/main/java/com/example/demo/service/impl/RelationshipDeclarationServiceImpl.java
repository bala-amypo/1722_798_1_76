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
        if (person.getRelationshipDeclared() == null || !person.getRelationshipDeclared()) {
            person.setRelationshipDeclared(true);
            personRepository.save(person);
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




// package com.example.demo.service.impl;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.PersonProfile;
// import com.example.demo.model.RelationshipDeclaration;
// import com.example.demo.repository.PersonProfileRepository;
// import com.example.demo.repository.RelationshipDeclarationRepository;
// import com.example.demo.service.RelationshipDeclarationService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
//     private final RelationshipDeclarationRepository relationshipRepository;
//     private final PersonProfileRepository personRepository;
    
//     public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipRepository,
//                                              PersonProfileRepository personRepository) {
//         this.relationshipRepository = relationshipRepository;
//         this.personRepository = personRepository;
//     }
    
//     @Override
//     public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
//         // Check if person exists
//         PersonProfile person = personRepository.findById(declaration.getPersonId())
//                 .orElseThrow(() -> new ApiException("Person not found"));
        
//         // Update person's relationshipDeclared flag
//         if (!person.getRelationshipDeclared()) {
//             person.setRelationshipDeclared(true);
//             personRepository.save(person);
//         }
        
//         // Set default isVerified if null
//         if (declaration.getIsVerified() == null) {
//             declaration.setIsVerified(false);
//         }
        
//         return relationshipRepository.save(declaration);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
//         return relationshipRepository.findByPersonId(personId);
//     }
    
//     @Override
//     public RelationshipDeclaration verifyDeclaration(Long declarationId, boolean verified) {
//         RelationshipDeclaration declaration = relationshipRepository.findById(declarationId)
//                 .orElseThrow(() -> new ApiException("Declaration not found"));
        
//         declaration.setIsVerified(verified);
//         return relationshipRepository.save(declaration);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getAllDeclarations() {
//         return relationshipRepository.findAll();
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.PersonProfile;
// import com.example.demo.repository.PersonProfileRepository;
// import com.example.demo.service.PersonProfileService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class PersonProfileServiceImpl implements PersonProfileService {
    
//     private final PersonProfileRepository personRepository;
    
//     public PersonProfileServiceImpl(PersonProfileRepository personRepository) {
//         this.personRepository = personRepository;
//     }
    
//     @Override
//     public PersonProfile createPerson(PersonProfile person) {
//         if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
//             throw new ApiException("Email is required");
//         }
        
//         // Check for duplicate email
//         Optional<PersonProfile> existingByEmail = personRepository.findByEmail(person.getEmail());
//         if (existingByEmail.isPresent()) {
//             throw new ApiException("Duplicate email");
//         }
        
//         // Check for duplicate reference ID if provided
//         if (person.getReferenceId() != null && !person.getReferenceId().trim().isEmpty()) {
//             Optional<PersonProfile> existingByRefId = personRepository.findByReferenceId(person.getReferenceId());
//             if (existingByRefId.isPresent()) {
//                 throw new ApiException("Duplicate reference");
//             }
//         }
        
//         // Set defaults
//         if (person.getRelationshipDeclared() == null) {
//             person.setRelationshipDeclared(false);
//         }
        
//         return personRepository.save(person);
//     }
    
//     @Override
//     public PersonProfile getPersonById(Long id) {
//         return personRepository.findById(id)
//                 .orElseThrow(() -> new ApiException("Person not found"));
//     }
    
//     @Override
//     public List<PersonProfile> getAllPersons() {
//         return personRepository.findAll();
//     }
    
//     @Override
//     public Optional<PersonProfile> findByReferenceId(String referenceId) {
//         return personRepository.findByReferenceId(referenceId);
//     }
    
//     @Override
//     public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
//         PersonProfile person = getPersonById(id);
//         person.setRelationshipDeclared(declared);
//         return personRepository.save(person);
//     }
// }



// package com.example.demo.service.impl;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.PersonProfile;
// import com.example.demo.model.RelationshipDeclaration;
// import com.example.demo.repository.PersonProfileRepository;
// import com.example.demo.repository.RelationshipDeclarationRepository;
// import com.example.demo.service.RelationshipDeclarationService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
//     private final RelationshipDeclarationRepository relationshipRepository;
//     private final PersonProfileRepository personRepository;
    
//     public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipRepository,
//                                              PersonProfileRepository personRepository) {
//         this.relationshipRepository = relationshipRepository;
//         this.personRepository = personRepository;
//     }
    
//     @Override
//     public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
//         // Check if person exists
//         PersonProfile person = personRepository.findById(declaration.getPersonId())
//                 .orElseThrow(() -> new ApiException("Person not found"));
        
//         // Update person's relationshipDeclared flag
//         if (!person.getRelationshipDeclared()) {
//             person.setRelationshipDeclared(true);
//             personRepository.save(person);
//         }
        
//         // Set default isVerified if null
//         if (declaration.getIsVerified() == null) {
//             declaration.setIsVerified(false);
//         }
        
//         return relationshipRepository.save(declaration);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
//         return relationshipRepository.findByPersonId(personId);
//     }
    
//     @Override
//     public RelationshipDeclaration verifyDeclaration(Long declarationId, boolean verified) {
//         RelationshipDeclaration declaration = relationshipRepository.findById(declarationId)
//                 .orElseThrow(() -> new ApiException("Declaration not found"));
        
//         declaration.setIsVerified(verified);
//         return relationshipRepository.save(declaration);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getAllDeclarations() {
//         return relationshipRepository.findAll();
//     }
// }