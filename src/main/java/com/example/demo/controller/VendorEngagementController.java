// package com.example.demo.controller;

// import com.example.demo.model.VendorEngagementRecord;
// import com.example.demo.service.VendorEngagementService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/engagements")
// @Tag(name = "Vendor Engagement", description = "Vendor Engagement Management")
// public class VendorEngagementController {
    
//     private final VendorEngagementService engagementService;
    
//     public VendorEngagementController(VendorEngagementService engagementService) {
//         this.engagementService = engagementService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Add vendor engagement")
//     public ResponseEntity<VendorEngagementRecord> addEngagement(@RequestBody VendorEngagementRecord record) {
//         VendorEngagementRecord saved = engagementService.addEngagement(record);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }
    
//     @GetMapping("/employee/{employeeId}")
//     @Operation(summary = "Get engagements by employee")
//     public ResponseEntity<List<VendorEngagementRecord>> getByEmployee(@PathVariable Long employeeId) {
//         List<VendorEngagementRecord> engagements = engagementService.getEngagementsByEmployee(employeeId);
//         return ResponseEntity.ok(engagements);
//     }
    
//     @GetMapping("/vendor/{vendorId}")
//     @Operation(summary = "Get engagements by vendor")
//     public ResponseEntity<List<VendorEngagementRecord>> getByVendor(@PathVariable Long vendorId) {
//         List<VendorEngagementRecord> engagements = engagementService.getEngagementsByVendor(vendorId);
//         return ResponseEntity.ok(engagements);
//     }
    
//     @GetMapping
//     @Operation(summary = "List all engagements")
//     public ResponseEntity<List<VendorEngagementRecord>> getAll() {
//         List<VendorEngagementRecord> engagements = engagementService.getAllEngagements();
//         return ResponseEntity.ok(engagements);
//     }
// }


package com.example.demo.controller;

import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.service.VendorEngagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engagements")
public class VendorEngagementController {
    
    private final VendorEngagementService engagementService;
    
    public VendorEngagementController(VendorEngagementService engagementService) {
        this.engagementService = engagementService;
    }
    
    @PostMapping
    public ResponseEntity<VendorEngagementRecord> addEngagement(@RequestBody VendorEngagementRecord record) {
        try {
            VendorEngagementRecord saved = engagementService.addEngagement(record);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<VendorEngagementRecord>> getEngagementsByEmployee(@PathVariable Long employeeId) {
        List<VendorEngagementRecord> engagements = engagementService.getEngagementsByEmployee(employeeId);
        return ResponseEntity.ok(engagements);
    }
    
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorEngagementRecord>> getEngagementsByVendor(@PathVariable Long vendorId) {
        List<VendorEngagementRecord> engagements = engagementService.getEngagementsByVendor(vendorId);
        return ResponseEntity.ok(engagements);
    }
    
    @GetMapping
    public ResponseEntity<List<VendorEngagementRecord>> getAllEngagements() {
        List<VendorEngagementRecord> engagements = engagementService.getAllEngagements();
        return ResponseEntity.ok(engagements);
    }
}