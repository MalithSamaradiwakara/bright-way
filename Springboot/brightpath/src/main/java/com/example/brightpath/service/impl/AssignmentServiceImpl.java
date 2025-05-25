package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Assignment;
import com.example.brightpath.exception.ResourceNotFoundException;
import com.example.brightpath.repository.AssignmentRepository;
import com.example.brightpath.repository.SubmitRepository;
import com.example.brightpath.service.AssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SubmitRepository submitRepository;

    @Override
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public Assignment getAssignmentById(Integer id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + id));
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment updateAssignment(Integer id, Assignment assignment) {
        assignment.setAssignmentId(id);
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public void deleteAssignment(Integer id) {
        if (!assignmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Assignment not found with ID: " + id);
        }
        submitRepository.deleteByAssignment_AssignmentId(id);
        assignmentRepository.deleteById(id);
    }

    @Override
    public List<Assignment> getAssignmentsByStudentId(Long studentId) {
        return assignmentRepository.findByStudent_Id(studentId);
    }

    @Override
    public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
        return assignmentRepository.findByTeacher_tId(teacherId);
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(Integer courseId) {
        return assignmentRepository.findByCourse_Id(courseId);
    }



}
