// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.entity.ConflictCase;
// import java.util.List;

// public interface ConflictCaseRepository extends JpaRepository<ConflictCase, Long> {
//     List<ConflictCase> findByPrimaryPersonIdOrSecondaryPersonId(Long primaryPersonId, Long secondaryPersonId);
// }

// ConflictCaseRepository.java
package com.example.demo.repository;

import com.example.demo.model.ConflictCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConflictCaseRepository extends JpaRepository<ConflictCase, Long> {
    @Query("SELECT c FROM ConflictCase c WHERE c.primaryPersonId = :personId OR c.secondaryPersonId = :personId")
    List<ConflictCase> findByPrimaryPersonIdOrSecondaryPersonId(@Param("personId") Long personId);
}