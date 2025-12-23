// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.entity.VendorEngagementRecord;
// import com.example.demo.repository.VendorEngagementRecordRepository;
// import com.example.demo.service.VendorEngagementService;
// import java.util.List;

// @Service
// public class VendorEngagementServiceImpl implements VendorEngagementService {
    
//     private final VendorEngagementRecordRepository vendorEngagementRecordRepository;
    
//     public VendorEngagementServiceImpl(VendorEngagementRecordRepository vendorEngagementRecordRepository) {
//         this.vendorEngagementRecordRepository = vendorEngagementRecordRepository;
//     }
    
//     @Override
//     public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {
//         return vendorEngagementRecordRepository.save(record);
//     }
    
//     @Override
//     public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
//         return vendorEngagementRecordRepository.findByEmployeeId(employeeId);
//     }
    
//     @Override
//     public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
//         return vendorEngagementRecordRepository.findByVendorId(vendorId);
//     }
    
//     @Override
//     public List<VendorEngagementRecord> getAllEngagements() { 
//         return vendorEngagementRecordRepository.findAll();
//     }
// }




// VendorEngagementServiceImpl.java
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
    
    private final VendorEngagementRecordRepository vendorEngagementRecordRepository;
    private final PersonProfileRepository personProfileRepository;
    
    public VendorEngagementServiceImpl(
            VendorEngagementRecordRepository vendorEngagementRecordRepository,
            PersonProfileRepository personProfileRepository) {
        this.vendorEngagementRecordRepository = vendorEngagementRecordRepository;
        this.personProfileRepository = personProfileRepository;
    }
    
    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {
        // Validate employee exists and is an employee
        PersonProfile employee = personProfileRepository.findById(record.getEmployeeId())
            .orElseThrow(() -> new ApiException("Employee not found with id: " + record.getEmployeeId()));
        
        if (!"EMPLOYEE".equalsIgnoreCase(employee.getPersonType())) {
            throw new ApiException("Person with id " + record.getEmployeeId() + " is not an employee");
        }
        
        // Validate vendor exists and is a vendor
        PersonProfile vendor = personProfileRepository.findById(record.getVendorId())
            .orElseThrow(() -> new ApiException("Vendor not found with id: " + record.getVendorId()));
        
        if (!"VENDOR".equalsIgnoreCase(vendor.getPersonType())) {
            throw new ApiException("Person with id " + record.getVendorId() + " is not a vendor");
        }
        
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