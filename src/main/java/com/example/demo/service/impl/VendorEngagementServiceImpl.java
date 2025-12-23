package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorEngagementServiceImpl implements VendorEngagementService {
    
    private final VendorEngagementRecordRepository engagementRepository;
    private final PersonProfileRepository personRepository;
    
    public VendorEngagementServiceImpl(VendorEngagementRecordRepository engagementRepository,
                                      PersonProfileRepository personRepository) {
        this.engagementRepository = engagementRepository;
        this.personRepository = personRepository;
    }
    
    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {
        // Check if employee exists
        personRepository.findById(record.getEmployeeId())
                .orElseThrow(() -> new ApiException("Employee not found"));
        
        // Check if vendor exists
        personRepository.findById(record.getVendorId())
                .orElseThrow(() -> new ApiException("Vendor not found"));
        
        return engagementRepository.save(record);
    }
    
    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return engagementRepository.findByEmployeeId(employeeId);
    }
    
    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
        return engagementRepository.findByVendorId(vendorId);
    }
    
    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return engagementRepository.findAll();
    }
}