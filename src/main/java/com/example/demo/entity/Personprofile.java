// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;

// @Entity
// @Table(name = "person_profiles",
// uniqueConstraints = {
//     @UniqueConstraint(columnNames = "email"),
//     @UniqueConstraint(columnNames = "referenceId")
// })
// public class Personprofile {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Long id;
//     private String personType;
//     private String referenceId;
//     private String fullName;
//     private String email;
//     private String department;
//     private boolean relationshipDeclared = false;
//     private LocalDateTime createdAt = LocalDateTime.now();
    
//     public Personprofile() {
//     }
    
//     public Personprofile(Long id, String personType, String referenceId, String fullName, String email,
//             String department, boolean relationshipDeclared, LocalDateTime createdAt) {
//         this.id = id;
//         this.personType = personType;
//         this.referenceId = referenceId;
//         this.fullName = fullName;
//         this.email = email;
//         this.department = department;
//         this.relationshipDeclared = relationshipDeclared;
//         this.createdAt = createdAt;
//     }
    
//     public Long getId() {
//         return id;
//     }
    
//     public void setId(Long id) {
//         this.id = id;
//     }
    
//     public String getPersonType() {
//         return personType;
//     }
    
//     public void setPersonType(String personType) {
//         this.personType = personType;
//     }
    
//     public String getReferenceId() {
//         return referenceId;
//     }
    
//     public void setReferenceId(String referenceId) {
//         this.referenceId = referenceId;
//     }
    
//     public String getFullName() {
//         return fullName;
//     }
    
//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }
    
//     public String getEmail() {
//         return email;
//     }
    
//     public void setEmail(String email) {
//         this.email = email;
//     }
    
//     public String getDepartment() {
//         return department;
//     }
    
//     public void setDepartment(String department) {
//         this.department = department;
//     }
    
//     public boolean isRelationshipDeclared() {
//         return relationshipDeclared;
//     }
    
//     public void setRelationshipDeclared(boolean relationshipDeclared) {
//         this.relationshipDeclared = relationshipDeclared;
//     }
    
//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }
    
//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }
// }   


package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "person_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personprofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Reference ID is required")
    @Column(name = "reference_id", unique = true, nullable = false)
    private String referenceId;

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "person_type")
    private String personType; // EMPLOYEE, VENDOR, APPLICANT

    private String department;
    
    @Column(name = "relationship_declared")
    private Boolean relationshipDeclared = false;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt = java.time.LocalDateTime.now();
}