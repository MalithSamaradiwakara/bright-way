package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Attempt;
import com.example.brightpath.entity.AttemptId;
import com.example.brightpath.repository.AttemptRepository;
import com.example.brightpath.service.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptServiceImpl implements AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public Attempt createAttempt(Attempt attempt) {
        return attemptRepository.save(attempt);
    }

    @Override
    public Attempt updateAttempt(Attempt attempt) {
        return attemptRepository.save(attempt);
    }

    @Override
    public Attempt getAttemptById(Integer quizId, Integer studentId) {
        AttemptId id = new AttemptId(quizId, studentId);
        return attemptRepository.findById(id).orElse(null);
    }

    @Override
    public List<Attempt> getAllAttempts() {
        return attemptRepository.findAll();
    }

    @Override
    public void deleteAttempt(Integer quizId, Integer studentId) {
        attemptRepository.deleteById(new AttemptId(quizId, studentId));
    }

    @Override
    public List<Attempt> getAttemptsByStudentId(Long studentId) {
        return attemptRepository.findByStudent_Id(studentId);
    }

    @Override
    public List<Attempt> getAttemptsByQuizId(Integer quizId) {
        return attemptRepository.findByQuiz_QuizId(quizId);
    }

    @Override
    public void deleteByQuiz_QuizId(Integer quizId){
        attemptRepository.deleteByQuiz_QuizId(quizId);
    }
}
