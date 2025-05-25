package com.example.brightpath.controller;

import com.example.brightpath.entity.Submit;
import com.example.brightpath.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    @PostMapping
    public Submit createSubmission(@RequestBody Submit submit) {
        return submitService.createSubmission(submit);
    }

    @PutMapping("/assignment/{assignmentId}/student/{studentId}")
    public Submit updateSubmission(
            @PathVariable Integer assignmentId,
            @PathVariable Integer studentId,
            @RequestBody Submit submit) {
        return submitService.updateSubmission(assignmentId, studentId, submit);
    }

    @GetMapping
    public List<Submit> getAllSubmissions() {
        return submitService.getAllSubmissions();
    }

    @GetMapping("/student/{studentId}")
    public List<Submit> getSubmissionsByStudentId(@PathVariable Long studentId) {
        return submitService.getSubmissionsByStudentId(studentId);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submit> getSubmissionsByAssignmentId(@PathVariable Integer assignmentId) {
        return submitService.getSubmissionsByAssignmentId(assignmentId);
    }

    @DeleteMapping({"/{id}"})
    public void deleteSubmission(@PathVariable Integer id) {
        submitService.deleteSubmissionBySubmitId(id);
    }
}
