package com.example.brightpath.repository;

import com.example.brightpath.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // Find questions by Quiz ID
    List<Question> findByQuiz_QuizId(Integer quizId);

    @Modifying
    @Query("DELETE FROM Question q WHERE q.quiz.quizId = :quizId")
    void deleteByQuizQuizId(@Param("quizId") Integer quizId);
}
