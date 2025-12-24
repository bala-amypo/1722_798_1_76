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
//         // Check for required fields
//         if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
//             throw new ApiException("Email is required");
//         }
        
//         // Check for duplicate email
//         personRepository.findByEmail(person.getEmail()).ifPresent(p -> {
//             throw new ApiException("Duplicate email");
//         });
        
//         // Check for duplicate reference ID if provided
//         if (person.getReferenceId() != null && !person.getReferenceId().trim().isEmpty()) {
//             personRepository.findByReferenceId(person.getReferenceId()).ifPresent(p -> {
//                 throw new ApiException("Duplicate reference");
//             });
//         }
        
//         // Set default relationshipDeclared if null
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



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {
    
    private final PersonProfileRepository personRepository;
    
    public PersonProfileServiceImpl(PersonProfileRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @Override
    public PersonProfile createPerson(PersonProfile person) {
        // Check for required fields
        if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
            throw new ApiException("Email is required");
        }
        
        if (person.getReferenceId() == null || person.getReferenceId().trim().isEmpty()) {
            throw new ApiException("Reference ID is required");
        }
        
        // Check for duplicate email
        Optional<PersonProfile> existingByEmail = personRepository.findByEmail(person.getEmail());
        if (existingByEmail.isPresent()) {
            throw new ApiException("Duplicate email");
        }
        
        // Check for duplicate reference ID
        Optional<PersonProfile> existingByRefId = personRepository.findByReferenceId(person.getReferenceId());
        if (existingByRefId.isPresent()) {
            throw new ApiException("Duplicate reference");
        }
        
        // Set default relationshipDeclared if null
        if (person.getRelationshipDeclared() == null) {
            person.setRelationshipDeclared(false);
        }
        
        return personRepository.save(person);
    }
    
    @Override
    public PersonProfile getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }
    
    @Override
    public List<PersonProfile> getAllPersons() {
        return personRepository.findAll();
    }
    
    @Override
    public Optional<PersonProfile> findByReferenceId(String referenceId) {
        return personRepository.findByReferenceId(referenceId);
    }
    
    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return personRepository.save(person);
    }
}