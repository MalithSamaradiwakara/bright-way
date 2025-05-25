package com.example.brightpath.repository;

import com.example.brightpath.entity.Submit;
import com.example.brightpath.entity.SubmitId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmitRepository extends JpaRepository<Submit, SubmitId> {
    List<Submit> findByStudent_Id(Long studentId);
    List<Submit> findByAssignment_AssignmentId(Integer assignmentId);

    void deleteByAssignment_AssignmentId(Integer assignmentId);
}
