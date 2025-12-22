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

package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.entity.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;

import java.util.List;
import java.util.Optional;

public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repo;

    public PersonProfileServiceImpl(PersonProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public PersonProfile createPerson(PersonProfile p) {

        if (p.getEmail() == null) {
            throw new ApiException("email required");
        }

        if (repo.findByEmail(p.getEmail()).isPresent()) {
            throw new ApiException("duplicate email");
        }

        if (p.getReferenceId() != null &&
            repo.findByReferenceId(p.getReferenceId()).isPresent()) {
            throw new ApiException("duplicate reference");
        }

        return repo.save(p);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repo.findAll();
    }

    @Override
    public Optional<PersonProfile> findByReferenceId(String ref) {
        return repo.findByReferenceId(ref);
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean flag) {
        PersonProfile p = getPersonById(id);
        p.setRelationshipDeclared(flag);
        return repo.save(p);
    }
}
