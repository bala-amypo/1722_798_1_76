
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "relationship_declaration")
public class RelationshipDeclaration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "person_id")
    private Long personId;
    
    @Column(name = "related_person_name")
    private String relatedPersonName;
    
    @Column(name = "relationship_type")
    private String relationshipType;
    
    private String description;
    
    @Column(name = "declared_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime declaredAt = LocalDateTime.now();
    
    @Column(name = "is_verified")
    private Boolean isVerified = false;
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }
    
    public String getRelatedPersonName() { return relatedPersonName; }
    public void setRelatedPersonName(String relatedPersonName) { this.relatedPersonName = relatedPersonName; }
    
    public String getRelationshipType() { return relationshipType; }
    public void setRelationshipType(String relationshipType) { this.relationshipType = relationshipType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getDeclaredAt() { return declaredAt; }
    public void setDeclaredAt(LocalDateTime declaredAt) { this.declaredAt = declaredAt; }
    
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
}




















// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "relationship_declaration")
// public class RelationshipDeclaration {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(name = "person_id")
//     private Long personId;
    
//     @Column(name = "related_person_name")
//     private String relatedPersonName;
    
//     @Column(name = "relationship_type")
//     private String relationshipType;
    
//     private String description;
    
//     @Column(name = "declared_at")
//     private LocalDateTime declaredAt = LocalDateTime.now();
    
//     @Column(name = "is_verified")
//     private Boolean isVerified = false;
    
//     // Constructors
//     public RelationshipDeclaration() {}
    
//     // Getters and Setters
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
    
//     public Boolean getIsVerified() {
//         return isVerified;
//     }
    
//     public void setIsVerified(Boolean isVerified) {
//         this.isVerified = isVerified;
//     }
// }
