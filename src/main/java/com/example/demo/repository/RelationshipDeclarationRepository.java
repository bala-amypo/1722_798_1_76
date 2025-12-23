// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.entity.RelationshipDeclaration;
// import java.util.List;

// public interface RelationshipDeclarationRepository extends JpaRepository<RelationshipDeclaration, Long> {
//     List<RelationshipDeclaration> findByPersonId(Long personId);
// }



// RelationshipDeclarationRepository.java
package com.example.demo.repository;

import com.example.demo.model.RelationshipDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipDeclarationRepository extends JpaRepository<RelationshipDeclaration, Long> {
}