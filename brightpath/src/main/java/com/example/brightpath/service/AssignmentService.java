package com.example.brightpath.service;

import com.example.brightpath.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(Assignment assignment);
    Assignment getAssignmentById(Integer id);
    List<Assignment> getAllAssignments();
    Assignment updateAssignment(Integer id, Assignment assignment);
    void deleteAssignment(Integer id);

    // Extra methods
    List<Assignment> getAssignmentsByStudentId(Long studentId);
    List<Assignment> getAssignmentsByTeacherId(Integer teacherId);
    List<Assignment> getAssignmentsByCourseId(Integer courseId);
}
