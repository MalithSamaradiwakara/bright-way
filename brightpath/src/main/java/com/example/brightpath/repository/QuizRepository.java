package com.example.brightpath.repository;

import com.example.brightpath.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // Find quizzes by Student ID
    List<Quiz> findByStudent_Id(Long studentId);

    // Find quizzes by Course ID
    List<Quiz> findByCourse_Id(Integer courseId);

    // Find quizzes by Teacher ID
    List<Quiz> findByTeacher_tId(Integer teacherId);
}
