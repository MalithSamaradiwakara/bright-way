package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Student;
import com.example.brightpath.entity.Login;
import com.example.brightpath.entity.Enroll;
import com.example.brightpath.entity.Submit;
import com.example.brightpath.entity.Attempt;
import com.example.brightpath.repository.*;
import com.example.brightpath.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_PATTERN = "^\\d{10}$";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private SubmitRepository submitRepository;

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public Student registerStudent(Student student) {
        validateStudent(student);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        
        if (StringUtils.hasText(studentDetails.getName())) {
            student.setName(studentDetails.getName());
        }
        if (StringUtils.hasText(studentDetails.getEmail())) {
            validateEmail(studentDetails.getEmail());
            student.setEmail(studentDetails.getEmail());
        }
        if (StringUtils.hasText(studentDetails.getContact())) {
            validatePhone(studentDetails.getContact());
            student.setContact(studentDetails.getContact());
        }
        if (StringUtils.hasText(studentDetails.getPhoto())) {
            student.setPhoto(studentDetails.getPhoto());
        }
        
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        
        // Delete related login record
        Login login = loginRepository.findByStudentId(id);
        if (login != null) {
            loginRepository.delete(login);
        }
        
        // Delete related enrollments
        List<Enroll> enrollments = enrollRepository.findByStudentId(id);
        enrollRepository.deleteAll(enrollments);
        
        // Delete related submissions
        List<Submit> submissions = submitRepository.findByStudent_Id(id);
        submitRepository.deleteAll(submissions);
        
        // Delete related attempts
        List<Attempt> attempts = attemptRepository.findByStudent_Id(id);
        attemptRepository.deleteAll(attempts);
        
        // Finally delete the student
        studentRepository.delete(student);
    }

    private void validateStudent(Student student) {
        if (!StringUtils.hasText(student.getName())) {
            throw new IllegalArgumentException("Student name is required");
        }
        validateEmail(student.getEmail());
        validatePhone(student.getContact());
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validatePhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new IllegalArgumentException("Contact number is required");
        }
        if (!Pattern.matches(PHONE_PATTERN, phone)) {
            throw new IllegalArgumentException("Invalid phone number format. Must be 10 digits");
        }
    }
} 