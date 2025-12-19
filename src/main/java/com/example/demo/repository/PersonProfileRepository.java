package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Personprofile;

public interface PersonProfileRepository extends JpaRepository<Personprofile, Long> {
    Optional<Personprofile> findByEmail(String email);
    Optional<Personprofile> findByReferenceId(String referenceId);
}