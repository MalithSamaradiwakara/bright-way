package com.example.brightpath.repository;

import com.example.brightpath.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    
    Student findByEmail(String email);
}
