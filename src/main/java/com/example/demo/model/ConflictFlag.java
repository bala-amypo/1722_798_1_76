package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conflict_flag")
public class ConflictFlag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "case_id")
    private Long caseId;
    
    @Column(name = "flag_type")
    private String flagType;
    
    private String description;
    
    private String severity;
    
    @Column(name = "flagged_at")
    private LocalDateTime flaggedAt = LocalDateTime.now();
    
    // Constructors
    public ConflictFlag() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCaseId() {
        return caseId;
    }
    
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
    
    public String getFlagType() {
        return flagType;
    }
    
    public void setFlagType(String flagType) {
        this.flagType = flagType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    public LocalDateTime getFlaggedAt() {
        return flaggedAt;
    }
    
    public void setFlaggedAt(LocalDateTime flaggedAt) {
        this.flaggedAt = flaggedAt;
    }
}