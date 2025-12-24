// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;

// @Entity
// @Table(name = "vendor_engagement_record")
// public class VendorEngagementRecord {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(name = "employee_id")
//     private Long employeeId;
    
//     @Column(name = "vendor_id")
//     private Long vendorId;
    
//     @Column(name = "engagement_type")
//     private String engagementType;
    
//     private Double amount;
    
//     @Column(name = "engagement_date")
//     private LocalDate engagementDate;
    
//     private String notes;
    
//     // Constructors
//     public VendorEngagementRecord() {}
    
//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }
    
//     public void setId(Long id) {
//         this.id = id;
//     }
    
//     public Long getEmployeeId() {
//         return employeeId;
//     }
    
//     public void setEmployeeId(Long employeeId) {
//         this.employeeId = employeeId;
//     }
    
//     public Long getVendorId() {
//         return vendorId;
//     }
    
//     public void setVendorId(Long vendorId) {
//         this.vendorId = vendorId;
//     }
    
//     public String getEngagementType() {
//         return engagementType;
//     }
    
//     public void setEngagementType(String engagementType) {
//         this.engagementType = engagementType;
//     }
    
//     public Double getAmount() {
//         return amount;
//     }
    
//     public void setAmount(Double amount) {
//         this.amount = amount;
//     }
    
//     public LocalDate getEngagementDate() {
//         return engagementDate;
//     }
    
//     public void setEngagementDate(LocalDate engagementDate) {
//         this.engagementDate = engagementDate;
//     }
    
//     public String getNotes() {
//         return notes;
//     }
    
//     public void setNotes(String notes) {
//         this.notes = notes;
//     }
// }


package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vendor_engagement_record")
public class VendorEngagementRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "vendor_id")
    private Long vendorId;
    
    @Column(name = "engagement_type")
    private String engagementType;
    
    private Double amount;
    
    @Column(name = "engagement_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate engagementDate;
    
    private String notes;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
    
    public String getEngagementType() { return engagementType; }
    public void setEngagementType(String engagementType) { this.engagementType = engagementType; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public LocalDate getEngagementDate() { return engagementDate; }
    public void setEngagementDate(LocalDate engagementDate) { this.engagementDate = engagementDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}