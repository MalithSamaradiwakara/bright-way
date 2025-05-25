package com.example.brightpath.controller;

import com.example.brightpath.entity.Admin;
import com.example.brightpath.entity.Login;
import com.example.brightpath.entity.LoginRequest;
import com.example.brightpath.entity.Student;
import com.example.brightpath.entity.Teacher;
import com.example.brightpath.repository.AdminRepository;
import com.example.brightpath.repository.LoginRepository;
import com.example.brightpath.repository.StudentRepository;
import com.example.brightpath.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login/{id}")
    public ResponseEntity<?> getLoginById(@PathVariable String id) {
        try {
            if (id == null || id.equals("undefined")) {
                return ResponseEntity.badRequest().body("Invalid login ID");
            }

            Long loginId = Long.parseLong(id);
            Login login = loginRepository.findById(loginId)
                .orElse(null);
            
            if (login == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> response = new HashMap<>();
            response.put("loginId", login.getLoginId());
            response.put("username", login.getUsername());
            response.put("userType", login.getUserType());
            
            if (login.getStudent() != null) {
                response.put("studentId", login.getStudent().getId());
                response.put("name", login.getStudent().getName());
            }
            if (login.getTeacher() != null) {
                response.put("teacherId", login.getTeacher().gettId());
                response.put("name", login.getTeacher().gettName());
            }
            if (login.getAdmin() != null) {
                response.put("adminId", login.getAdmin().getId());
                response.put("name", login.getAdmin().getName());
            }

            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid login ID format");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Login login = loginRepository.findByUsername(loginRequest.getUsername());
        
        if (login == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), login.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }

        // Verify that the requested userType matches the one in the database
        String requestedUserType = loginRequest.getUserType();
        if (requestedUserType != null && !requestedUserType.equals(login.getUserType().toString())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid user type"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", login.getLoginId());
        response.put("userType", login.getUserType().toString());
        
        // Add user-specific information based on type
        switch (login.getUserType()) {
            case Student:
                if (login.getStudent() != null) {
                    response.put("studentId", login.getStudent().getId());
                    response.put("name", login.getStudent().getName());
                }
                break;
            case Teacher:
                if (login.getTeacher() != null) {
                    response.put("teacherId", login.getTeacher().gettId());
                    response.put("name", login.getTeacher().gettName());
                }
                break;
            case Admin:
                if (login.getAdmin() != null) {
                    response.put("adminId", login.getAdmin().getId());
                    response.put("name", login.getAdmin().getName());
                }
                break;
        }

        return ResponseEntity.ok(response);
    }
}