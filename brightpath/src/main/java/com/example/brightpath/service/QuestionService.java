package com.example.brightpath.service;

import com.example.brightpath.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestionById(Integer id);
    List<Question> getAllQuestions();
    Question updateQuestion(Integer id, Question question);
    void deleteQuestion(Integer id);

    // Extra method
    List<Question> getQuestionsByQuizId(Integer quizId);
}
