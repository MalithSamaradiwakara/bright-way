package com.example.brightpath.dto;

import java.util.List;

public class CourseDTO {
    private Long id;
    private String title;
    private String icon;
    private String description;
    private String fullDescription;
    private String duration;
    private String level;
    private String prerequisites;
    private Double price;
    private List<String> topics;
    private TeacherReference teacher;
    
    // Inner class for teacher reference
    public static class TeacherReference {
        private int tId;
        
        public TeacherReference() {}
        
        public TeacherReference(int tId) {
            this.tId = tId;
        }
        
        public int gettId() {
            return tId;
        }
        
        public void settId(int tId) {
            this.tId = tId;
        }
    }
    
    // Constructors
    public CourseDTO() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFullDescription() {
        return fullDescription;
    }
    
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public String getPrerequisites() {
        return prerequisites;
    }
    
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public List<String> getTopics() {
        return topics;
    }
    
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    
    public TeacherReference getTeacher() {
        return teacher;
    }
    
    public void setTeacher(TeacherReference teacher) {
        this.teacher = teacher;
    }
}