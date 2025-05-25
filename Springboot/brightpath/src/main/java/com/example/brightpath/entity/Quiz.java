package com.example.brightpath.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Quiz_ID")
    private Integer quizId;

    @ManyToOne
    @JoinColumn(name = "S_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "C_ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "T_ID")
    private Teacher teacher;

    @Column(name = "Q_Availability")
    private Boolean availability;

    @Column(name = "Q_Title")
    private String title;

    @Column(name = "Q_Description")
    private String description;


}
