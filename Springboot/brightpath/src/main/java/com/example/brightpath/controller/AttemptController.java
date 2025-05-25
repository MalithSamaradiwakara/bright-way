package com.example.brightpath.controller;

import com.example.brightpath.entity.Attempt;
import com.example.brightpath.entity.AttemptId;
import com.example.brightpath.service.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @PostMapping
    public Attempt createAttempt(@RequestBody Attempt attempt) {
        return attemptService.createAttempt(attempt);
    }

    @PutMapping("/quiz/{quizId}/student/{studentId}")
    public Attempt updateAttempt(
            @PathVariable Integer quizId,
            @PathVariable Integer studentId,
            @RequestBody Attempt attempt) {
        attempt.setId(new AttemptId(quizId, studentId));
        return attemptService.updateAttempt(attempt);
    }

    @GetMapping
    public List<Attempt> getAllAttempts() {
        return attemptService.getAllAttempts();
    }

    @GetMapping("/quiz/{quizId}")
    public List<Attempt> getAttemptsByQuizId(@PathVariable Integer quizId) {
        return attemptService.getAttemptsByQuizId(quizId);
    }

    @GetMapping("/student/{studentId}")
    public List<Attempt> getAttemptsByStudentId(@PathVariable Long studentId) {
        return attemptService.getAttemptsByStudentId(studentId);
    }

    @DeleteMapping({"/{quizId}"})
    public void deleteAttemptByQuizId(@PathVariable Integer quizId) {
        attemptService.deleteByQuiz_QuizId(quizId);
    }

}
