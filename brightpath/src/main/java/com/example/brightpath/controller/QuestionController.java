package com.example.brightpath.controller;

import com.example.brightpath.entity.Question;
import com.example.brightpath.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }

    // Extra method: Get questions by Quiz ID
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Integer quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }
}
