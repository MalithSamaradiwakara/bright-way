package com.example.brightpath.controller;

import com.example.brightpath.entity.Quiz;
import com.example.brightpath.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Integer id) {
        return quizService.getQuizById(id);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Integer id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }

//    @DeleteMapping("/{id}")
//    public void deleteQuiz(@PathVariable Integer id) {
//        quizService.deleteQuiz(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Integer id) {
        try {
            quizService.deleteQuiz(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Quiz not found with id: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to delete quiz: " + e.getMessage()));
        }
    }

    @GetMapping("/course/{courseId}")
    public List<Quiz> getQuizzesByCourseId(@PathVariable Integer courseId) {
        return quizService.getQuizzesByCourseId(courseId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Quiz> getQuizzesByTeacherId(@PathVariable Integer teacherId) {
        return quizService.getQuizzesByTeacherId(teacherId);
    }

    @GetMapping("/student/{studentId}")
    public List<Quiz> getQuizzesByStudentId(@PathVariable Long studentId) {
        return quizService.getQuizzesByStudentId(studentId);
    }
}
