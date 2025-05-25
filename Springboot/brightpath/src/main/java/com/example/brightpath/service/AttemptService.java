package com.example.brightpath.service;

import com.example.brightpath.entity.Attempt;

import java.util.List;

public interface AttemptService {
    Attempt createAttempt(Attempt attempt);
    Attempt updateAttempt(Attempt attempt);
    Attempt getAttemptById(Integer quizId, Integer studentId);
    List<Attempt> getAllAttempts();
    void deleteAttempt(Integer quizId, Integer studentId);
    List<Attempt> getAttemptsByStudentId(Long studentId);
    List<Attempt> getAttemptsByQuizId(Integer quizId);

    void deleteByQuiz_QuizId(Integer quizId);
}
