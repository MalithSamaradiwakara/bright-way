package com.example.brightpath.service;

import com.example.brightpath.entity.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    Quiz getQuizById(Integer id);
    List<Quiz> getAllQuizzes();
    Quiz updateQuiz(Integer id, Quiz quiz);
    void deleteQuiz(Integer id);

    // Extra methods
    List<Quiz> getQuizzesByStudentId(Long studentId);
    List<Quiz> getQuizzesByCourseId(Integer courseId);
    List<Quiz> getQuizzesByTeacherId(Integer teacherId);


}
