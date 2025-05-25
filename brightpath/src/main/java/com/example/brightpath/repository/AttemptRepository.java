package com.example.brightpath.repository;

import com.example.brightpath.entity.Attempt;
import com.example.brightpath.entity.AttemptId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, AttemptId> {
    List<Attempt> findByStudent_Id(Long studentId);
    List<Attempt> findByQuiz_QuizId(Integer quizId);

    void deleteByQuiz_QuizId(Integer quizId);
}
