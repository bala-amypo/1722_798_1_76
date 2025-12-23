// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.RelationshipDeclaration;

// public interface RelationshipDeclarationService {
//     RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration);
//     RelationshipDeclaration verify(Long id, boolean verified);
//     List<RelationshipDeclaration> getDeclarationsByPerson(Long personId);
//     List<RelationshipDeclaration> getAllDeclarations();
// }



// RelationshipDeclarationService.java
// RelationshipDeclarationService.java
package com.example.demo.service;

import com.example.demo.model.RelationshipDeclaration;
import java.util.List;

public interface RelationshipDeclarationService {
    RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration);
    RelationshipDeclaration verifyDeclaration(Long id, Boolean isVerified);
    List<RelationshipDeclaration> getAllDeclarations();
}