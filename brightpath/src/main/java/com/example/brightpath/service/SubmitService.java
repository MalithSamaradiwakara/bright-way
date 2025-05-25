package com.example.brightpath.service;

import com.example.brightpath.entity.Submit;

import java.util.List;

public interface SubmitService {
    Submit createSubmission(Submit submit);
    Submit updateSubmission(Integer assignmentId, Integer studentId, Submit submit);
    List<Submit> getAllSubmissions();
    List<Submit> getSubmissionsByStudentId(Long studentId);
    List<Submit> getSubmissionsByAssignmentId(Integer assignmentId);

    void deleteSubmissionBySubmitId(Integer id);
}
