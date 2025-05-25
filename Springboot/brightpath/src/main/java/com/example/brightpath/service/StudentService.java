package com.example.brightpath.service;

import com.example.brightpath.entity.Student;
import java.util.List;

public interface StudentService {
    // Create a new student
    Student registerStudent(Student student);
    
    // Get all students
    List<Student> getAllStudents();
    
    // Get student by ID
    Student getStudentById(Long id);
    
    // Update student
    Student updateStudent(Long id, Student student);
    
    // Delete student
    void deleteStudent(Long id);
} 