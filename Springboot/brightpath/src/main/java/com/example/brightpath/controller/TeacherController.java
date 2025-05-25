package com.example.brightpath.controller;

import com.example.brightpath.dto.TeacherRegistrationDTO;
import com.example.brightpath.entity.Teacher;
import com.example.brightpath.exception.ResourceNotFoundException;
import com.example.brightpath.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // ✅ Create Teacher WITH Login credentials
    @PostMapping("/register")
    public ResponseEntity<?> registerTeacher(@Validated @RequestBody TeacherRegistrationDTO dto) {
        try {
            // Validate fields
            if (dto.gettName() == null || dto.gettName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (dto.gettEmail() == null || dto.gettEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (dto.gettPhoto() == null || dto.gettPhoto().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Photo is required");
            }
            if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }
            if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }

            Teacher savedTeacher = teacherService.registerTeacherWithLogin(dto);
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering teacher: " + e.getMessage());
        }
    }

    // 📘 Get All Teachers
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    // 📘 Get Teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    // ✏️ Update Teacher
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @Validated @RequestBody Teacher teacherDetails) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
        return ResponseEntity.ok(updatedTeacher);
    }

    // ❌ Delete Teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    // ➕ Add Qualification
    @PostMapping("/{teacherId}/qualifications")
    public ResponseEntity<Teacher> addQualification(@PathVariable int teacherId, @RequestParam String qualification) {
        Teacher updatedTeacher = teacherService.addQualification(teacherId, qualification);
        return ResponseEntity.ok(updatedTeacher);
    }

    // ➖ Remove Qualification
    @DeleteMapping("/{teacherId}/qualifications")
    public ResponseEntity<Void> removeQualification(@PathVariable int teacherId, @RequestParam String qualification) {
        teacherService.removeQualification(teacherId, qualification);
        return ResponseEntity.noContent().build();
    }

    // 📘 Get All Qualifications
    @GetMapping("/{teacherId}/qualifications")
    public ResponseEntity<Set<String>> getQualifications(@PathVariable int teacherId) {
        Set<String> qualifications = teacherService.getQualificationsByTeacherId(teacherId);
        return ResponseEntity.ok(qualifications);
    }

    // 🔁 Replace All Qualifications
    @PutMapping("/{teacherId}/qualifications/full")
    public ResponseEntity<Teacher> updateAllQualifications(@PathVariable int teacherId,
            @RequestBody Teacher teacherRequest) {
        Set<String> newQualifications = teacherRequest.getQualification();
        Teacher updatedTeacher = teacherService.replaceQualifications(teacherId, newQualifications);
        return ResponseEntity.ok(updatedTeacher);
    }

    // ❗ Custom Exception Handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
