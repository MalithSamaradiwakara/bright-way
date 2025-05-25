package com.example.brightpath.controller;

import com.example.brightpath.dto.EnrollmentDetailsDTO;
import com.example.brightpath.entity.Enroll;
import com.example.brightpath.entity.EnrollId;
import com.example.brightpath.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/enrollments")
public class AdminEnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    /**
     * Get all enrollments for admin dashboard
     */
    @GetMapping
    public ResponseEntity<List<EnrollmentDetailsDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentDetails());
    }
    
    /**
     * Get enrollments by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EnrollmentDetailsDTO>> getEnrollmentsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentDetailsByStatus(status));
    }
    
    /**
     * Get enrollment statistics for dashboard
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getEnrollmentStatistics() {
        return ResponseEntity.ok(enrollmentService.getEnrollmentStatistics());
    }
    
    /**
     * Get payment slip file
     */
    @GetMapping("/payment-slip/{filepath:.+}")
    public ResponseEntity<Resource> getPaymentSlip(@PathVariable String filepath) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filepath).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists()) {
                String contentType = "application/octet-stream";
                if (filepath.endsWith(".pdf")) {
                    contentType = "application/pdf";
                } else if (filepath.endsWith(".jpg") || filepath.endsWith(".jpeg")) {
                    contentType = "image/jpeg";
                } else if (filepath.endsWith(".png")) {
                    contentType = "image/png";
                }
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}