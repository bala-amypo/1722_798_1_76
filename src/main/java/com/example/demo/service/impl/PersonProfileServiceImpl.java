// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.Personprofile;
// import com.example.demo.repository.PersonProfileRepository;
// import com.example.demo.service.PersonProfileService;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class PersonProfileServiceImpl implements PersonProfileService {
    
//     private final PersonProfileRepository personProfileRepository;
    
//     public PersonProfileServiceImpl(PersonProfileRepository personProfileRepository) {
//         this.personProfileRepository = personProfileRepository;
//     }
    
//     @Override
//     public Personprofile createPerson(Personprofile person) {
//         Optional<Personprofile> existingByEmail = personProfileRepository.findByEmail(person.getEmail());
//         if (existingByEmail.isPresent()) {
//             return null;
//         }
        
//         Optional<Personprofile> existingByRefId = personProfileRepository.findByReferenceId(person.getReferenceId());
//         if (existingByRefId.isPresent()) {
//             return null;
//         }
        
//         return personProfileRepository.save(person);
//     }
    
//     @Override
//     public Personprofile getPersonById(Long id) {
//         return personProfileRepository.findById(id).orElse(null);
//     }
    
//     @Override
//     public List<Personprofile> getAllPerson() {
//         return personProfileRepository.findAll();
//     }
    
//     @Override
//     public Personprofile updateRelationshipDeclared(Long id, boolean declared) {
//         Optional<Personprofile> optionalPerson = personProfileRepository.findById(id);
//         if (optionalPerson.isPresent()) {
//             Personprofile person = optionalPerson.get();
//             person.setRelationshipDeclared(declared);
//             return personProfileRepository.save(person);
//         }
//         return null;
//     }
    
//     @Override
//     public Personprofile findByReferenceId(String referenceId) {
//         return personProfileRepository.findByReferenceId(referenceId).orElse(null);
//     }
// }



// PersonProfileServiceImpl.java
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
    
    private final PersonProfileRepository personProfileRepository;
    
    public PersonProfileServiceImpl(PersonProfileRepository personProfileRepository) {
        this.personProfileRepository = personProfileRepository;
    }
    
    @Override
    public PersonProfile createPerson(PersonProfile person) {
        // Test expects validation for missing email (test03)
        if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
            throw new ApiException("Email is required");
        }
        
        // Test expects duplicate email check (test16, test43)
        if (personProfileRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new ApiException("Person with email " + person.getEmail() + " already exists");
        }
        
        // Test expects duplicate referenceId check (test33)
        if (person.getReferenceId() != null && 
            personProfileRepository.findByReferenceId(person.getReferenceId()).isPresent()) {
            throw new ApiException("Person with reference ID " + person.getReferenceId() + " already exists");
        }
        
        return personProfileRepository.save(person);
    }
    
    @Override
    public PersonProfile getPersonById(Long id) {
        // Test expects ApiException when not found (test04)
        return personProfileRepository.findById(id)
            .orElseThrow(() -> new ApiException("Person not found with id: " + id));
    }
    
    @Override
    public PersonProfile updateRelationshipDeclared(Long id, Boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return personProfileRepository.save(person);
    }
    
    @Override
    public List<PersonProfile> getAllPersons() {
        return personProfileRepository.findAll();
    }
    
    @Override
    public Optional<PersonProfile> findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId);
    }
}