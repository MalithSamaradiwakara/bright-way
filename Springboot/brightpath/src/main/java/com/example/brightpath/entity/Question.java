package com.example.brightpath.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_ID")
    private Integer questionId;

    @Column(name = "Question")
    private String question;

    @Column(name = "Answers")
    private String answers;

    @Column(name = "Correct")
    private Integer correct;

    @ManyToOne
    @JoinColumn(name = "Quiz_ID")
    private Quiz quiz;
}
