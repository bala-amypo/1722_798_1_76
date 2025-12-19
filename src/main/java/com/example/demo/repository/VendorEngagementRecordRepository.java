package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.VendorEngagementRecord;
import java.util.List;

public interface VendorEngagementRecordRepository extends JpaRepository<VendorEngagementRecord, Long> {
    List<VendorEngagementRecord> findByEmployeeId(Long employeeId);
    List<VendorEngagementRecord> findByVendorId(Long vendorId);
}