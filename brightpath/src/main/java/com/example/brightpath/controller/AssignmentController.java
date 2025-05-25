package com.example.brightpath.controller;

import com.example.brightpath.entity.Assignment;
import com.example.brightpath.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignmentService.createAssignment(assignment);
    }

    @GetMapping("/{id}")
    public Assignment getAssignmentById(@PathVariable Integer id) {
        return assignmentService.getAssignmentById(id);
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @PutMapping("/{id}")
    public Assignment updateAssignment(@PathVariable Integer id, @RequestBody Assignment assignment) {
        return assignmentService.updateAssignment(id, assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAssignment(@PathVariable Integer id) {
        try{
            assignmentService.deleteAssignment(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Assignment not found with id: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to delete Assignment: " + e.getMessage()));
        }
    }

    // Extra methods
    @GetMapping("/student/{studentId}")
    public List<Assignment> getAssignmentsByStudentId(@PathVariable Long studentId) {
        return assignmentService.getAssignmentsByStudentId(studentId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Assignment> getAssignmentsByTeacherId(@PathVariable Integer teacherId) {
        return assignmentService.getAssignmentsByTeacherId(teacherId);
    }

    @GetMapping("/course/{courseId}")
    public List<Assignment> getAssignmentsByCourseId(@PathVariable Integer courseId) {
        return assignmentService.getAssignmentsByCourseId(courseId);
    }
}
