// RelationshipDeclaration.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "relationship_declaration")
public class RelationshipDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long personId;
    
    @Column(nullable = false)
    private String relatedPersonName;
    
    @Column(nullable = false)
    private String relationshipType; // FAMILY, BUSINESS, FRIENDSHIP
    
    private Boolean isVerified = false;
    private String notes;
    
    @Column(name = "declared_at")
    private LocalDateTime declaredAt;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }
    
    public String getRelatedPersonName() { return relatedPersonName; }
    public void setRelatedPersonName(String relatedPersonName) { this.relatedPersonName = relatedPersonName; }
    
    public String getRelationshipType() { return relationshipType; }
    public void setRelationshipType(String relationshipType) { this.relationshipType = relationshipType; }
    
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getDeclaredAt() { return declaredAt; }
    public void setDeclaredAt(LocalDateTime declaredAt) { this.declaredAt = declaredAt; }
    
    @PrePersist
    protected void onCreate() {
        declaredAt = LocalDateTime.now();
        if (isVerified == null) {
            isVerified = false;
        }
    }
}