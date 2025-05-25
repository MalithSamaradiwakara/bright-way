package com.example.brightpath.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Login_ID")
    private Long loginId;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "User_Type", nullable = false)
    private UserType userType;

    @OneToOne
    @JoinColumn(name = "S_ID", referencedColumnName = "S_ID", nullable = true)
    private Student student;

    @OneToOne
    @JoinColumn(name = "T_ID", referencedColumnName = "T_ID", nullable = true)
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "Admin_ID", referencedColumnName = "Admin_ID", nullable = true)
    private Admin admin;

    public enum UserType {
        Teacher,
        Student,
        Admin
    }

    // Getters and Setters
    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
