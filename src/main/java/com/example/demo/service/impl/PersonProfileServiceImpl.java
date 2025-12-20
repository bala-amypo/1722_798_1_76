package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Personprofile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {
    
    private final PersonProfileRepository personProfileRepository;
    
    public PersonProfileServiceImpl(PersonProfileRepository personProfileRepository) {
        this.personProfileRepository = personProfileRepository;
    }
    
    @Override
    public Personprofile createPerson(Personprofile person) {
        // Check for duplicate email
        Optional<Personprofile> existingByEmail = personProfileRepository.findByEmail(person.getEmail());
        if (existingByEmail.isPresent()) {
            return null; // Duplicate email
        }
        
        // Check for duplicate referenceId
        Optional<Personprofile> existingByRefId = personProfileRepository.findByReferenceId(person.getReferenceId());
        if (existingByRefId.isPresent()) {
            return null; // Duplicate reference ID
        }
        
        return personProfileRepository.save(person);
    }
    
    @Override
    public Personprofile getPersonById(Long id) {
        return personProfileRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Personprofile> getAllPerson() {
        return personProfileRepository.findAll();
    }
    
    @Override
    public Personprofile updateRelationshipDeclared(Long id, boolean declared) {
        Optional<Personprofile> optionalPerson = personProfileRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Personprofile person = optionalPerson.get();
            person.setRelationshipDeclared(declared);
            return personProfileRepository.save(person);
        }
        return null;
    }
    
    @Override
    public Personprofile findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId).orElse(null);
    }
}