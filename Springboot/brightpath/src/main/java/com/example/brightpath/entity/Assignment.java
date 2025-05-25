package com.example.brightpath.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "S_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "C_ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "T_ID")
    private Teacher teacher;

    @Column(name = "A_Availability")
    private Boolean availability;

    @Column(name = "A_Title")
    private String title;

    @Column(name = "A_Description")
    private String description;

    @Column(name = "A_File_Path")
    private String filePath;
}
