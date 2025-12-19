package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.VendorEngagementRecord;

public interface VendorEngagementService {

    VendorEngagementRecord addEngagement(VendorEngagementRecord record);

    List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId);

    List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId);

    List<VendorEngagementService> getAllEngagements();


    
}
