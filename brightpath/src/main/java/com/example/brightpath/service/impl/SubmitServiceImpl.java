package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Submit;
import com.example.brightpath.entity.SubmitId;
import com.example.brightpath.repository.SubmitRepository;
import com.example.brightpath.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    private SubmitRepository submitRepository;

    @Override
    public Submit createSubmission(Submit submit) {
        return submitRepository.save(submit);
    }

    @Override
    public Submit updateSubmission(Integer assignmentId, Integer studentId, Submit submit) {
        SubmitId submitId = new SubmitId(assignmentId, studentId.longValue());
        Submit existingSubmit = submitRepository.findById(submitId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        existingSubmit.setMarks(submit.getMarks());
        existingSubmit.setFeedback(submit.getFeedback());
        existingSubmit.setSubmissionDate(submit.getSubmissionDate());
        existingSubmit.setFilePath(submit.getFilePath());

        return submitRepository.save(existingSubmit);
    }

    @Override
    public List<Submit> getAllSubmissions() {
        return submitRepository.findAll();
    }

    @Override
    public List<Submit> getSubmissionsByStudentId(Long studentId) {
        return submitRepository.findByStudent_Id(studentId);
    }

    @Override
    public List<Submit> getSubmissionsByAssignmentId(Integer assignmentId) {
        return submitRepository.findByAssignment_AssignmentId(assignmentId);
    }

    @Override
    public void deleteSubmissionBySubmitId(Integer id) {
        submitRepository.deleteByAssignment_AssignmentId(id);
    }
}
