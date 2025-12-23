// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "relationship_declarations")
// public class RelationshipDeclaration {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private Long personId;
//     private String relatedPersonName;
//     private String relationshipType;
//     private String description;
//     private LocalDateTime declaredAt = LocalDateTime.now();
//     private boolean isVerified = false;
    
//     public RelationshipDeclaration() {
//     }
    
//     public RelationshipDeclaration(Long id, Long personId, String relatedPersonName, String relationshipType,
//             String description, LocalDateTime declaredAt, boolean isVerified) {
//         this.id = id;
//         this.personId = personId;
//         this.relatedPersonName = relatedPersonName;
//         this.relationshipType = relationshipType;
//         this.description = description;
//         this.declaredAt = declaredAt;
//         this.isVerified = isVerified;
//     }
    
//     public Long getId() {
//         return id;
//     }
    
//     public void setId(Long id) {
//         this.id = id;
//     }
    
//     public Long getPersonId() {
//         return personId;
//     }
    
//     public void setPersonId(Long personId) {
//         this.personId = personId;
//     }
    
//     public String getRelatedPersonName() {
//         return relatedPersonName;
//     }
    
//     public void setRelatedPersonName(String relatedPersonName) {
//         this.relatedPersonName = relatedPersonName;
//     }
    
//     public String getRelationshipType() {
//         return relationshipType;
//     }
    
//     public void setRelationshipType(String relationshipType) {
//         this.relationshipType = relationshipType;
//     }
    
//     public String getDescription() {
//         return description;
//     }
    
//     public void setDescription(String description) {
//         this.description = description;
//     }
    
//     public LocalDateTime getDeclaredAt() {
//         return declaredAt;
//     }
    
//     public void setDeclaredAt(LocalDateTime declaredAt) {
//         this.declaredAt = declaredAt;
//     }
    
//     public boolean isVerified() {
//         return isVerified;
//     }
    
//     public void setVerified(boolean isVerified) {
//         this.isVerified = isVerified;
//     }
// }



package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "relationship_declarations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @NotBlank(message = "Related person name is required")
    @Column(name = "related_person_name", nullable = false)
    private String relatedPersonName;

    @NotBlank(message = "Relationship type is required")
    @Column(name = "relationship_type", nullable = false)
    private String relationshipType; // FAMILY, BUSINESS, OTHER

    @Column(name = "relationship_details")
    private String relationshipDetails;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "declared_at")
    private java.time.LocalDate declaredAt = java.time.LocalDate.now();

    @Column(name = "verified_at")
    private java.time.LocalDate verifiedAt;
}