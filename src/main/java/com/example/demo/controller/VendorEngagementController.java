package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VendorEngagementRecord;
import com.example.demo.service.VendorEngagementService;

@RestController
@RequestMapping("/api/engagements")
public class VendorEngagementController {
    
    private VendorEngagementService service;

    public VendorEngagementController(VendorEngagementService service) {
        this.service = service;
    }
    @PostMapping
    public VendorEngagementRecord add(@RequestBody VendorEngagementRecord record){
        return service.addEngagement(record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<VendorEngagementRecord>getByEmployee(@PathVariable Long employeeId){
        return service.getEngagementsByEmployee(employeeId);
    }
    @GetMapping("/vendor/{vendorid}")
    public List<VendorEngagementRecord>getByVendor(@PathVariable Long vendorId){
        return service.getEngagementsByVendor(vendorId);
    }
    @GetMapping
    public List<VendorEngagementService>getAll(){
        return service.getAllEngagements();
    }

    

    
}
