package com.example.brightpath.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Submit")
public class Submit {

    @EmbeddedId
    private SubmitId id;

    @ManyToOne
    @MapsId("assignmentId")
    @JoinColumn(name = "A_ID")
    private Assignment assignment;

    @ManyToOne
    @MapsId("sId")
    @JoinColumn(name = "S_ID")
    private Student student;

    @Column(name = "A_Marks")
    private Double marks;

    @Column(name = "A_Feedback")
    private String feedback;

    @Column(name = "Sub_Date")
    private Date submissionDate;

    @Column(name = "S_File_path")
    private String filePath;
}
