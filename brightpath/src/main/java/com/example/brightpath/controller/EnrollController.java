package com.example.brightpath.controller;

import com.example.brightpath.entity.Course;
import com.example.brightpath.entity.Enroll;
import com.example.brightpath.entity.EnrollId;
import com.example.brightpath.entity.Student;
import com.example.brightpath.repository.CourseRepository;
import com.example.brightpath.repository.EnrollRepository;
import com.example.brightpath.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody Map<String, Object> enrollmentData) {
        try {
            Long studentId = Long.parseLong(enrollmentData.get("studentId").toString());
            Long courseId = Long.parseLong(enrollmentData.get("courseId").toString());
            String paymentRef = (String) enrollmentData.get("paymentRef");
            Date enrollDate = new SimpleDateFormat("yyyy-MM-dd").parse(
                    enrollmentData.get("date").toString());
            
            // Validate student exists
            Optional<Student> student = studentRepository.findById(studentId);
            if (!student.isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Student not found"));
            }
            
            // Validate course exists
            Optional<Course> course = courseRepository.findById(courseId);
            if (!course.isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Course not found"));
            }
            
            // Check if enrollment already exists
            if (enrollRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
                return ResponseEntity.badRequest().body(Map.of("message", "You are already enrolled in this course"));
            }
            
            // Create new enrollment
            Enroll enrollment = new Enroll();
            enrollment.setStudent(student.get());
            enrollment.setCourse(course.get());
            enrollment.setEnrollDate(enrollDate);
            enrollment.setPaymentRefNum(paymentRef);
            enrollment.setStatus("Pending"); // Set default status to Pending
            
            // Set payment slip path if available
            if (enrollmentData.containsKey("paymentSlipPath")) {
                enrollment.setPaymentSlipPath((String) enrollmentData.get("paymentSlipPath"));
            }
            
            enrollRepository.save(enrollment);
            
            // Create and return the composite key for reference
            EnrollId enrollId = enrollment.getId();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Enrollment successful");
            response.put("studentId", enrollId.getStudentId());
            response.put("courseId", enrollId.getCourseId());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error creating enrollment: " + e.getMessage()));
        }
    }
    
    @PostMapping("/upload-payment")
    public ResponseEntity<?> uploadPaymentSlip(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId) {
        
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Please select a file to upload"));
        }
        
        try {
            // Create upload directory if it doesn't exist
            String uploadPath = uploadDir + File.separator + "payments";
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Create a unique filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = "payment_" + studentId + "_" + courseId + "_" + timestamp + fileExtension;
            
            // Save the file
            Path filePath = Paths.get(uploadPath + File.separator + filename);
            Files.write(filePath, file.getBytes());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment slip uploaded successfully");
            response.put("filePath", "payments/" + filename);
            
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to upload payment slip: " + e.getMessage()));
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentEnrollments(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(enrollRepository.findByStudentId(studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error retrieving enrollments: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<?> getEnrollment(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Optional<Enroll> enrollment = enrollRepository.findById_StudentIdAndId_CourseId(studentId, courseId);
            if (enrollment.isPresent()) {
                return ResponseEntity.ok(enrollment.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error retrieving enrollment: " + e.getMessage()));
        }
    }
    
    // Get all pending enrollments for admin dashboard
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingEnrollments() {
        try {
            List<Enroll> pendingEnrollments = enrollRepository.findByStatus("Pending");
            return ResponseEntity.ok(pendingEnrollments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error retrieving pending enrollments: " + e.getMessage()));
        }
    }
    
    // Get all enrollments with details for admin
    @GetMapping("/all")
    public ResponseEntity<?> getAllEnrollments() {
        try {
            List<Enroll> enrollments = enrollRepository.findAll();
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error retrieving all enrollments: " + e.getMessage()));
        }
    }
    
    // Approve enrollment
    @PutMapping("/approve/{studentId}/{courseId}")
    public ResponseEntity<?> approveEnrollment(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Optional<Enroll> enrollmentOpt = enrollRepository.findById_StudentIdAndId_CourseId(studentId, courseId);
            if (!enrollmentOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            Enroll enrollment = enrollmentOpt.get();
            enrollment.setStatus("Approved");
            enrollRepository.save(enrollment);
            
            return ResponseEntity.ok(Map.of("message", "Enrollment approved successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error approving enrollment: " + e.getMessage()));
        }
    }
    
    // Reject enrollment
    @PutMapping("/reject/{studentId}/{courseId}")
    public ResponseEntity<?> rejectEnrollment(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Optional<Enroll> enrollmentOpt = enrollRepository.findById_StudentIdAndId_CourseId(studentId, courseId);
            if (!enrollmentOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            Enroll enrollment = enrollmentOpt.get();
            enrollment.setStatus("Rejected");
            enrollRepository.save(enrollment);
            
            return ResponseEntity.ok(Map.of("message", "Enrollment rejected successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error rejecting enrollment: " + e.getMessage()));
        }
    }

    @GetMapping("/student/{studentId}/approved")
    public ResponseEntity<?> getApprovedEnrollmentsForStudent(@PathVariable Long studentId) {
        try {
            List<Enroll> approvedEnrollments = enrollRepository.findApprovedByStudentId(studentId);
            
            // Transform the enrollments data to include complete course information
            List<Map<String, Object>> enrollmentDetails = approvedEnrollments.stream()
                .map(enrollment -> {
                    Map<String, Object> details = new HashMap<>();
                    Course course = enrollment.getCourse();
                    
                    details.put("enrollId", enrollment.getId());
                    details.put("studentId", enrollment.getStudent().getId());
                    details.put("courseId", course.getId());
                    details.put("enrollDate", enrollment.getEnrollDate());
                    details.put("status", enrollment.getStatus());
                    details.put("paymentRefNum", enrollment.getPaymentRefNum());
                    
                    // Add detailed course information
                    details.put("courseTitle", course.getTitle());
                    //details.put("courseCode", course.getCode());
                    details.put("courseDescription", course.getDescription());
                    //details.put("instructor", course.getInstructor());
                    details.put("category", course.getCategory());
                    //details.put("creditHours", course.getCreditHours());
                    
                    return details;
                })
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(enrollmentDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error retrieving approved enrollments: " + e.getMessage()));
        }
    }
}