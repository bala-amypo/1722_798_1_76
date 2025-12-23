// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.entity.ConflictFlag;
// import java.util.List;

// public interface ConflictFlagRepository extends JpaRepository<ConflictFlag, Long> {
//     List<ConflictFlag> findByCaseId(Long caseId);
// }


// ConflictFlagRepository.java
package com.example.demo.repository;

import com.example.demo.model.ConflictFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConflictFlagRepository extends JpaRepository<ConflictFlag, Long> {
    List<ConflictFlag> findByCaseId(Long caseId);
}