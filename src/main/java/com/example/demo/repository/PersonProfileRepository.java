// package com.example.demo.repository;

// import java.util.Optional;
// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.entity.Personprofile;

// public interface PersonProfileRepository extends JpaRepository<Personprofile, Long> {
//     Optional<Personprofile> findByEmail(String email);
//     Optional<Personprofile> findByReferenceId(String referenceId);
// }


// PersonProfileRepository.java
package com.example.demo.repository;

import com.example.demo.model.PersonProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonProfileRepository extends JpaRepository<PersonProfile, Long> {
    Optional<PersonProfile> findByEmail(String email);
    Optional<PersonProfile> findByReferenceId(String referenceId);
}