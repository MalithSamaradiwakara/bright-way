package com.example.brightpath.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentRegistrationDTO {
    // Student table fields
    @JsonProperty("S_Name")
    private String S_Name;
    
    @JsonProperty("S_Email")
    private String S_Email;
    
    @JsonProperty("S_Contact")
    private String S_Contact;
    
    @JsonProperty("S_Photo")
    private String S_Photo;
    
    // Login table fields
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;

    // Getters and Setters
    public String getS_Name() {
        return S_Name;
    }

    public void setS_Name(String S_Name) {
        this.S_Name = S_Name;
    }

    public String getS_Email() {
        return S_Email;
    }

    public void setS_Email(String S_Email) {
        this.S_Email = S_Email;
    }

    public String getS_Contact() {
        return S_Contact;
    }

    public void setS_Contact(String S_Contact) {
        this.S_Contact = S_Contact;
    }

    public String getS_Photo() {
        return S_Photo;
    }

    public void setS_Photo(String S_Photo) {
        this.S_Photo = S_Photo;
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

    @Override
    public String toString() {
        return "StudentRegistrationDTO{" +
                "S_Name='" + S_Name + '\'' +
                ", S_Email='" + S_Email + '\'' +
                ", S_Contact='" + S_Contact + '\'' +
                ", S_Photo='" + S_Photo + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
} 