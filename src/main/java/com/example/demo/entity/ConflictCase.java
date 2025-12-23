// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.*;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "conflict_cases")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class ConflictCase {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotNull(message = "Primary person ID is required")
//     @Column(name = "primary_person_id", nullable = false)
//     private Long primaryPersonId;

//     @NotNull(message = "Secondary person ID is required")
//     @Column(name = "secondary_person_id", nullable = false)
//     private Long secondaryPersonId;

//     @NotBlank(message = "Trigger source is required")
//     @Column(name = "trigger_source", nullable = false)
//     private String triggerSource;

//     @NotBlank(message = "Risk level is required")
//     @Column(name = "risk_level", nullable = false)
//     private String riskLevel; 

//     @Column(name = "status")
//     private String status = "OPEN"; 

//     @Column(name = "description")
//     private String description;

//     @Column(name = "resolution_notes")
//     private String resolutionNotes;

//     @Column(name = "created_at")
//     private LocalDateTime createdAt = LocalDateTime.now();

//     @Column(name = "updated_at")
//     private LocalDateTime updatedAt = LocalDateTime.now();

//     @Column(name = "resolved_at")
//     private LocalDateTime resolvedAt;
// }



// // package com.example.demo.entity;

// // import java.time.LocalDateTime;
// // import jakarta.persistence.Entity;
// // import jakarta.persistence.GeneratedValue;
// // import jakarta.persistence.GenerationType;
// // import jakarta.persistence.Id;

// // @Entity
// // public class ConflictCase {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;
// //     private Long primaryPersonId;
// //     private Long secondaryPersonId;
// //     private String triggerSource;
// //     private String riskLevel;
// //     private String details;
// //     private String status = "Open";
// //     private LocalDateTime detectedAt = LocalDateTime.now();
    
// //     public ConflictCase() {
// //     }

// //     public ConflictCase(Long id, Long primaryPersonId, Long secondaryPersonId, String triggerSource, String riskLevel,
// //             String details, String status, LocalDateTime detectedAt) {
// //         this.id = id;
// //         this.primaryPersonId = primaryPersonId;
// //         this.secondaryPersonId = secondaryPersonId;
// //         this.triggerSource = triggerSource;
// //         this.riskLevel = riskLevel;
// //         this.details = details;
// //         this.status = status;
// //         this.detectedAt = detectedAt;
// //     }

// //     public Long getId() {
// //         return id;
// //     }

// //     public void setId(Long id) {
// //         this.id = id;
// //     }

// //     public Long getPrimaryPersonId() {
// //         return primaryPersonId;
// //     }

// //     public void setPrimaryPersonId(Long primaryPersonId) {
// //         this.primaryPersonId = primaryPersonId;
// //     }

// //     public Long getSecondaryPersonId() {
// //         return secondaryPersonId;
// //     }

// //     public void setSecondaryPersonId(Long secondaryPersonId) {
// //         this.secondaryPersonId = secondaryPersonId;
// //     }

// //     public String getTriggerSource() {
// //         return triggerSource;
// //     }

// //     public void setTriggerSource(String triggerSource) {
// //         this.triggerSource = triggerSource;
// //     }

// //     public String getRiskLevel() {
// //         return riskLevel;
// //     }

// //     public void setRiskLevel(String riskLevel) {
// //         this.riskLevel = riskLevel;
// //     }

// //     public String getDetails() {
// //         return details;
// //     }

// //     public void setDetails(String details) {
// //         this.details = details;
// //     }

// //     public String getStatus() {
// //         return status;
// //     }

// //     public void setStatus(String status) {
// //         this.status = status;
// //     }

// //     public LocalDateTime getDetectedAt() {
// //         return detectedAt;
// //     }

// //     public void setDetectedAt(LocalDateTime detectedAt) {
// //         this.detectedAt = detectedAt;
// //     }
// // }



