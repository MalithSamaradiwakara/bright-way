package com.example.brightpath.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private Long id;
    
    // Replace @JsonBackReference with @JsonIgnoreProperties
    // This will include teacher info but ignore the courses list in teacher to prevent circular reference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "T_ID", nullable = false)
    @JsonIgnoreProperties({"courses"}) // This prevents infinite recursion
    private Teacher teacher;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Icon")
    private String icon;

    @Column(name = "C_Description")
    private String description;

    @Column(name = "Full_Description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name = "Duration", nullable = false)
    private String duration;

    @Column(name = "Level", nullable = false)
    private String level;

    @Column(name = "Prerequisites")
    private String prerequisites;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Is_Active", nullable = false)
    private boolean isActive;

    @Column(name = "Category")
    private String category = "General";

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "Course_topic",
        joinColumns = @JoinColumn(name = "C_ID"),
        foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (C_ID) REFERENCES Courses(C_ID) ON DELETE CASCADE")
    )
    @Column(name = "Topic")
    private List<String> topics;

    // Constructors
    public Course() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFullDescription() { return fullDescription; }
    public void setFullDescription(String fullDescription) { this.fullDescription = fullDescription; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<String> getTopics() { return topics; }
    public void setTopics(List<String> topics) { this.topics = topics; }
}