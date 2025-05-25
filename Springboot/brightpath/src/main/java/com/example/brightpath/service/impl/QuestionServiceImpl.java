package com.example.brightpath.service.impl;

import com.example.brightpath.entity.Question;
import com.example.brightpath.exception.ResourceNotFoundException;
import com.example.brightpath.repository.QuestionRepository;
import com.example.brightpath.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(Integer id, Question question) {
        question.setQuestionId(id);
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Integer id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with ID: " + id);
        }
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsByQuizId(Integer quizId) {
        return questionRepository.findByQuiz_QuizId(quizId);
    }
}
