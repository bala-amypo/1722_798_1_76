// ConflictFlag.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conflict_flag")
public class ConflictFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long caseId;
    
    @Column(nullable = false)
    private String flagType; // HIGH_AMOUNT, RELATIONSHIP, POLICY_VIOLATION
    
    @Column(nullable = false)
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    
    private String description;
    
    @Column(name = "raised_at")
    private LocalDateTime raisedAt;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCaseId() { return caseId; }
    public void setCaseId(Long caseId) { this.caseId = caseId; }
    
    public String getFlagType() { return flagType; }
    public void setFlagType(String flagType) { this.flagType = flagType; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getRaisedAt() { return raisedAt; }
    public void setRaisedAt(LocalDateTime raisedAt) { this.raisedAt = raisedAt; }
    
    @PrePersist
    protected void onCreate() {
        raisedAt = LocalDateTime.now();
    }
}