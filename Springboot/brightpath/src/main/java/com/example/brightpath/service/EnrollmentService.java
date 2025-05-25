package com.example.brightpath.service;

import com.example.brightpath.dto.EnrollmentDetailsDTO;
import com.example.brightpath.entity.Enroll;
import com.example.brightpath.repository.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollRepository enrollRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * Convert Enroll entity to EnrollmentDetailsDTO
     */
    public EnrollmentDetailsDTO convertToDTO(Enroll enroll) {
        EnrollmentDetailsDTO dto = new EnrollmentDetailsDTO();

        dto.setStudentId(enroll.getStudent().getId());
        dto.setStudentName(enroll.getStudent().getName());
        {
            /* + " " + enroll.getStudent().getName()); */}

        dto.setStudentEmail(enroll.getStudent().getEmail());

        dto.setCourseId(enroll.getCourse().getId());
        dto.setCourseName(enroll.getCourse().getTitle());
        // dto.setCourseCode(enroll.getCourse().getCode());
        dto.setCoursePrice(enroll.getCourse().getPrice());

        dto.setEnrollDate(enroll.getEnrollDate());
        dto.setPaymentRefNum(enroll.getPaymentRefNum());
        dto.setPaymentSlipPath(enroll.getPaymentSlipPath());
        dto.setStatus(enroll.getStatus());

        return dto;
    }

    /**
     * Get all enrollment details
     */
    public List<EnrollmentDetailsDTO> getAllEnrollmentDetails() {
        List<Enroll> enrollments = enrollRepository.findAll();
        return enrollments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get enrollment details by status
     */
    public List<EnrollmentDetailsDTO> getEnrollmentDetailsByStatus(String status) {
        List<Enroll> enrollments = enrollRepository.findByStatus(status);
        return enrollments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all enrollments for a specific student
     */
    public List<EnrollmentDetailsDTO> getStudentEnrollments(Long studentId) {
        List<Enroll> enrollments = enrollRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get approved enrollments for a specific student
     */
    public List<EnrollmentDetailsDTO> getApprovedStudentEnrollments(Long studentId) {
        List<Enroll> enrollments = enrollRepository.findApprovedByStudentId(studentId);
        return enrollments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get enrollment statistics for dashboard
     */
    public Map<String, Object> getEnrollmentStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Get enrollment counts by status
        List<Object[]> statusCounts = enrollRepository.countByStatus();
        Map<String, Long> statusMap = new HashMap<>();

        long totalEnrollments = 0;

        for (Object[] result : statusCounts) {
            String status = (String) result[0];
            Long count = (Long) result[1];
            statusMap.put(status, count);
            totalEnrollments += count;
        }

        stats.put("totalEnrollments", totalEnrollments);
        stats.put("pendingCount", statusMap.getOrDefault("Pending", 0L));
        stats.put("approvedCount", statusMap.getOrDefault("Approved", 0L));
        stats.put("rejectedCount", statusMap.getOrDefault("Rejected", 0L));

        return stats;
    }
}