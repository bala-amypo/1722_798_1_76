// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.VendorEngagementRecord;

// public interface VendorEngagementService {
//     VendorEngagementRecord addEngagement(VendorEngagementRecord record);
//     List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId);
//     List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId);
//     List<VendorEngagementRecord> getAllEngagements(); 
// }



// VendorEngagementService.java
package com.example.demo.service;

import com.example.demo.model.VendorEngagementRecord;
import java.util.List;

public interface VendorEngagementService {
    VendorEngagementRecord addEngagement(VendorEngagementRecord record);
    List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId);
    List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId);
    List<VendorEngagementRecord> getAllEngagements();
}