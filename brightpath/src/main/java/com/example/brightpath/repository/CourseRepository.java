package com.example.brightpath.repository;

import com.example.brightpath.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Removed the slug-related methods
}
