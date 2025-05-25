package com.example.brightpath.repository;

import com.example.brightpath.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    // Find assignments by Student ID
    List<Assignment> findByStudent_Id(Long student_id);

    // Find assignments by Teacher ID
    List<Assignment> findByTeacher_tId(Integer teacherId);

    // Find assignments by Course ID
    List<Assignment> findByCourse_Id(Integer courseId);
}
