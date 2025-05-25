package com.example.brightpath.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Attempt")
public class Attempt {

    @EmbeddedId
    private AttemptId id;

    @ManyToOne
    @MapsId("quizId")
    @JoinColumn(name = "Quiz_ID")
    private Quiz quiz;

    @ManyToOne
    @MapsId("sId")
    @JoinColumn(name = "S_ID")
    private Student student;

    @Column(name = "Sub_Date")
    private Date submissionDate;

    @Column(name = "Q_Feedback")
    private String feedback;

    @Column(name = "Q_Marks")
    private Double marks;
}
