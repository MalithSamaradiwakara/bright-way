package com.example.brightpath.dto;

import java.util.Date;

public class EnrollmentDetailsDTO {
    private Long studentId;
    private Long courseId;
    private String studentName;
    private String studentEmail;
    private String courseName;
    private String courseCode;
    private Double coursePrice;
    private Date enrollDate;
    private String paymentRefNum;
    private String paymentSlipPath;
    private String status;
    
    // Constructors
    public EnrollmentDetailsDTO() {
    }
    
    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public Double getCoursePrice() {
        return coursePrice;
    }
    
    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
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