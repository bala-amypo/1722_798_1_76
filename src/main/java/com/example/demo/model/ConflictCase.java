package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conflict_case")
public class ConflictCase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "primary_person_id")
    private Long primaryPersonId;
    
    @Column(name = "secondary_person_id")
    private Long secondaryPersonId;
    
    @Column(name = "trigger_source")
    private String triggerSource;
    
    @Column(name = "risk_level")
    private String riskLevel;
    
    private String details;
    
    private String status = "OPEN";
    
    @Column(name = "detected_at")
    private LocalDateTime detectedAt = LocalDateTime.now();
    
    // Constructors
    public ConflictCase() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getPrimaryPersonId() {
        return primaryPersonId;
    }
    
    public void setPrimaryPersonId(Long primaryPersonId) {
        this.primaryPersonId = primaryPersonId;
    }
    
    public Long getSecondaryPersonId() {
        return secondaryPersonId;
    }
    
    public void setSecondaryPersonId(Long secondaryPersonId) {
        this.secondaryPersonId = secondaryPersonId;
    }
    
    public String getTriggerSource() {
        return triggerSource;
    }
    
    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }
    
    public String getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }
    
    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }
}