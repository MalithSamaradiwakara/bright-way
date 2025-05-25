package com.example.brightpath.dto;

import java.util.Set;

public class TeacherRegistrationDTO {
    private String tName;
    private String tEmail;
    private String tDescription;
    private String tPhoto;
    private Set<String> qualification;

    private String username;
    private String password;

    // Getters and Setters

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettDescription() {
        return tDescription;
    }

    public void settDescription(String tDescription) {
        this.tDescription = tDescription;
    }

    public String gettPhoto() {
        return tPhoto;
    }

    public void settPhoto(String tPhoto) {
        this.tPhoto = tPhoto;
    }

    public Set<String> getQualification() {
        return qualification;
    }

    public void setQualification(Set<String> qualification) {
        this.qualification = qualification;
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
}
