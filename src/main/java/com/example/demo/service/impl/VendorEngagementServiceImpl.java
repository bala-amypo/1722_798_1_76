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



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.entity.VendorEngagementRecord;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;

import java.util.List;

public class VendorEngagementServiceImpl
        implements VendorEngagementService {

    private final VendorEngagementRecordRepository repo;
    private final PersonProfileRepository personRepo;

    public VendorEngagementServiceImpl(
            VendorEngagementRecordRepository repo,
            PersonProfileRepository personRepo) {
        this.repo = repo;
        this.personRepo = personRepo;
    }

    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord r) {

        personRepo.findById(r.getEmployeeId())
                .orElseThrow(() -> new ApiException("employee not found"));

        personRepo.findById(r.getVendorId())
                .orElseThrow(() -> new ApiException("vendor not found"));

        return repo.save(r);
    }

    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return repo.findAll();
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long id) {
        return repo.findByEmployeeId(id);
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long id) {
        return repo.findByVendorId(id);
    }
}
