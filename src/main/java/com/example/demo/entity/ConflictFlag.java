package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "conflict_flags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConflictFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Case ID is required")
    @Column(name = "case_id", nullable = false)
    private Long caseId;

    @NotBlank(message = "Flag type is required")
    @Column(name = "flag_type", nullable = false)
    private String flagType;

    @NotBlank(message = "Severity is required")
    @Column(name = "severity", nullable = false)
    private String severity; 

    @Column(name = "description")
    private String description;

    @Column(name = "is_resolved")
    private Boolean isResolved = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
}



// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class ConflictFlag {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private Long caseId;
//     private String flagType;
//     private String description;
//     private String severity;
//     private LocalDateTime flaggedAt = LocalDateTime.now();
    
//     public ConflictFlag() {
//     }
    
//     public ConflictFlag(Long id, Long caseId, String flagType, String description, String severity,
//             LocalDateTime flaggedAt) {
//         this.id = id;
//         this.caseId = caseId;
//         this.flagType = flagType;
//         this.description = description;
//         this.severity = severity;
//         this.flaggedAt = flaggedAt;
//     }
    
//     public Long getId() {
//         return id;
//     }
    
//     public void setId(Long id) {
//         this.id = id;
//     }
    
//     public Long getCaseId() {
//         return caseId;
//     }
    
//     public void setCaseId(Long caseId) {
//         this.caseId = caseId;
//     }
    
//     public String getFlagType() {
//         return flagType;
//     }
    
//     public void setFlagType(String flagType) {
//         this.flagType = flagType;
//     }
    
//     public String getDescription() {
//         return description;
//     }
    
//     public void setDescription(String description) {
//         this.description = description;
//     }
    
//     public String getSeverity() {
//         return severity;
//     }
    
//     public void setSeverity(String severity) {
//         this.severity = severity;
//     }
    
//     public LocalDateTime getFlaggedAt() {
//         return flaggedAt;
//     }
    
//     public void setFlaggedAt(LocalDateTime flaggedAt) {
//         this.flaggedAt = flaggedAt;
//     }
// }


