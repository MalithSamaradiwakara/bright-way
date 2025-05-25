package com.example.brightpath.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Enroll")
public class Enroll {

    @EmbeddedId
    private EnrollId id;

    @ManyToOne
    @MapsId("studentId") // Maps to the studentId property in EnrollId
    @JoinColumn(name = "S_ID", nullable = false)
    private Student student;

    @ManyToOne
    @MapsId("courseId") // Maps to the courseId property in EnrollId
    @JoinColumn(name = "C_ID", nullable = false)
    private Course course;

    @Column(name = "Date", nullable = false)
    private Date enrollDate;

    @Column(name = "Payment_Ref_Num", nullable = false)
    private String paymentRefNum;
    
    @Column(name = "Payment_Slip_Path")
    private String paymentSlipPath;
    
    @Column(name = "Status", nullable = false)
    private String status = "Pending"; // Pending, Approved, Rejected

    // Constructors
    public Enroll() {
        this.id = new EnrollId();
    }

    // Getters and Setters
    public EnrollId getId() {
        return id;
    }

    public void setId(EnrollId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if (this.id == null) {
            this.id = new EnrollId();
        }
        if (student != null) {
            this.id.setStudentId(student.getId());
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if (this.id == null) {
            this.id = new EnrollId();
        }
        if (course != null) {
            this.id.setCourseId(course.getId());
        }
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getPaymentRefNum() {
        return paymentRefNum;
    }

    public void setPaymentRefNum(String paymentRefNum) {
        this.paymentRefNum = paymentRefNum;
    }
    
    public String getPaymentSlipPath() {
        return paymentSlipPath;
    }
    
    public void setPaymentSlipPath(String paymentSlipPath) {
        this.paymentSlipPath = paymentSlipPath;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}