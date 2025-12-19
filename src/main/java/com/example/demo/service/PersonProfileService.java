package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Personprofile;

public interface PersonProfileService {
    Personprofile createPerson(Personprofile person);
    Personprofile getPersonById(Long id);
    List<Personprofile> getAllPerson();
    Personprofile updateRelationshipDeclared(Long id, boolean declared);
    Personprofile findByReferenceId(String referenceId);
}