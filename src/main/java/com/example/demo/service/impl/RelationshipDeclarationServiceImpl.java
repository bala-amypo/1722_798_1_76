// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.RelationshipDeclaration;
// import com.example.demo.repository.RelationshipDeclarationRepository;
// import com.example.demo.service.RelationshipDeclarationService;
// import java.util.List;

// @Service
// public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {
    
//     private final RelationshipDeclarationRepository relationshipDeclarationRepository;
    
//     public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository relationshipDeclarationRepository) {
//         this.relationshipDeclarationRepository = relationshipDeclarationRepository;
//     }
    
//     @Override
//     public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {
//         return relationshipDeclarationRepository.save(declaration);
//     }
    
//     @Override
//     public RelationshipDeclaration verify(Long id, boolean verified) {
//         RelationshipDeclaration declaration = relationshipDeclarationRepository.findById(id).orElse(null);
//         if (declaration != null) {
//             declaration.setVerified(verified);
//             return relationshipDeclarationRepository.save(declaration);
//         }
//         return null;
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
//         return relationshipDeclarationRepository.findByPersonId(personId);
//     }
    
//     @Override
//     public List<RelationshipDeclaration> getAllDeclarations() {
//         return relationshipDeclarationRepository.findAll();
//     }
// }



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.entity.RelationshipDeclaration;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;

import java.util.List;

public class RelationshipDeclarationServiceImpl
        implements RelationshipDeclarationService {

    private final RelationshipDeclarationRepository repo;
    private final PersonProfileRepository personRepo;

    public RelationshipDeclarationServiceImpl(
            RelationshipDeclarationRepository repo,
            PersonProfileRepository personRepo) {
        this.repo = repo;
        this.personRepo = personRepo;
    }

    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration d) {

        personRepo.findById(d.getPersonId())
                .orElseThrow(() -> new ApiException("person not found"));

        return repo.save(d);
    }

    @Override
    public RelationshipDeclaration verifyDeclaration(Long id, boolean flag) {
        RelationshipDeclaration d = repo.findById(id)
                .orElseThrow(() -> new ApiException("declaration not found"));

        d.setIsVerified(flag);
        return repo.save(d);
    }

    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return repo.findAll();
    }
}
