package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Quiz;
import com.example.brightpath.exception.ResourceNotFoundException;
import com.example.brightpath.repository.AttemptRepository;
import com.example.brightpath.repository.QuestionRepository;
import com.example.brightpath.repository.QuizRepository;
import com.example.brightpath.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + id));
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz updateQuiz(Integer id, Quiz quiz) {
        quiz.setQuizId(id);
        return quizRepository.save(quiz);
    }

//    @Override
//    public void deleteQuiz(Integer id) {
//        if (!quizRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Quiz not found with ID: " + id);
//        }
//        quizRepository.deleteById(id);
//    }

    @Override
    public List<Quiz> getQuizzesByStudentId(Long studentId) {
        return quizRepository.findByStudent_Id(studentId);
    }

    @Override
    public List<Quiz> getQuizzesByCourseId(Integer courseId) {
        return quizRepository.findByCourse_Id(courseId);
    }

    @Override
    public List<Quiz> getQuizzesByTeacherId(Integer teacherId) {
        return quizRepository.findByTeacher_tId(teacherId);
    }

    @Transactional
    public void deleteQuiz(Integer quizId) {
        // First delete all attempts
        attemptRepository.deleteByQuiz_QuizId(quizId);
        // delete all related questions
        questionRepository.deleteByQuizQuizId(quizId);

        // Then delete the quiz
        quizRepository.deleteById(quizId);

    }
}
