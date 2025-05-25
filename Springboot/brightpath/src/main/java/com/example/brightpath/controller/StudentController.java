package com.example.brightpath.controller;

import com.example.brightpath.dto.StudentRegistrationDTO;
import com.example.brightpath.entity.Student;
import com.example.brightpath.entity.Login;
import com.example.brightpath.service.StudentService;
import com.example.brightpath.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDTO registrationDTO) {
        try {
            // Validate student data
            if (registrationDTO.getS_Name() == null || registrationDTO.getS_Name().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (registrationDTO.getS_Email() == null || registrationDTO.getS_Email().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (registrationDTO.getS_Contact() == null || registrationDTO.getS_Contact().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Contact number is required");
            }
            if (registrationDTO.getS_Photo() == null || registrationDTO.getS_Photo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Photo is required");
            }
            if (registrationDTO.getUsername() == null || registrationDTO.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }
            if (registrationDTO.getPassword() == null || registrationDTO.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }

            // Create and save student
            Student student = new Student();
            student.setName(registrationDTO.getS_Name());
            student.setEmail(registrationDTO.getS_Email());
            student.setContact(registrationDTO.getS_Contact());
            student.setPhoto(registrationDTO.getS_Photo());
            
            Student savedStudent = studentService.registerStudent(student);
            
            // Create and save login credentials
            Login login = new Login();
            login.setUsername(registrationDTO.getUsername());
            login.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            login.setUserType(Login.UserType.Student);
            login.setStudent(savedStudent);
            
            loginRepository.save(login);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Student registered successfully");
            response.put("student", savedStudent);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error registering student: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching students: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getStudentProfile(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error fetching student profile: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            // Validate student data
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (student.getContact() == null || student.getContact().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Contact number is required");
            }
            if (student.getPhoto() == null || student.getPhoto().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Photo is required");
            }

            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error updating student: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 