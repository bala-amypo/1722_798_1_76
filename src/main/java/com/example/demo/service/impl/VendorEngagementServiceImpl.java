package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.VendorEngagementRecord;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;
import java.util.List;

@Service
public class VendorEngagementServiceImpl implements VendorEngagementService {
    
    private final VendorEngagementRecordRepository vendorEngagementRecordRepository;
    
    public VendorEngagementServiceImpl(VendorEngagementRecordRepository vendorEngagementRecordRepository) {
        this.vendorEngagementRecordRepository = vendorEngagementRecordRepository;
    }
    
    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {
        return vendorEngagementRecordRepository.save(record);
    }
    
    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return vendorEngagementRecordRepository.findByEmployeeId(employeeId);
    }
    
    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
        return vendorEngagementRecordRepository.findByVendorId(vendorId);
    }
    
    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return vendorEngagementRecordRepository.findAll();
    }
}