// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.Personprofile;

// public interface PersonProfileService {
//     Personprofile createPerson(Personprofile person);
//     Personprofile getPersonById(Long id);
//     List<Personprofile> getAllPerson();
//     Personprofile updateRelationshipDeclared(Long id, boolean declared);
//     Personprofile findByReferenceId(String referenceId);
// }


// PersonProfileService.java
package com.example.demo.service;

import com.example.demo.model.PersonProfile;
import java.util.List;
import java.util.Optional;

public interface PersonProfileService {
    PersonProfile createPerson(PersonProfile person);
    PersonProfile getPersonById(Long id);
    PersonProfile updateRelationshipDeclared(Long id, Boolean declared);
    List<PersonProfile> getAllPersons();
    Optional<PersonProfile> findByReferenceId(String referenceId);
}