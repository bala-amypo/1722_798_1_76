// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.entity.VendorEngagementRecord;
// import java.util.List;

// public interface VendorEngagementRecordRepository extends JpaRepository<VendorEngagementRecord, Long> {
//     List<VendorEngagementRecord> findByEmployeeId(Long employeeId);
//     List<VendorEngagementRecord> findByVendorId(Long vendorId);
// }


// VendorEngagementRecordRepository.java
package com.example.demo.repository;

import com.example.demo.model.VendorEngagementRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendorEngagementRecordRepository extends JpaRepository<VendorEngagementRecord, Long> {
    List<VendorEngagementRecord> findByEmployeeId(Long employeeId);
    List<VendorEngagementRecord> findByVendorId(Long vendorId);
}