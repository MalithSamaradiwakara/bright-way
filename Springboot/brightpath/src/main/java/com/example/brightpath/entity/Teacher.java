package com.example.brightpath.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table(name="Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private int tId;

    @Column(name = "T_Name")
    private String tName;

    @Column(name = "T_Email")
    private String tEmail;

    @Column(name = "T_Description")
    private String tDescription;

    @Column(name = "T_Photo")
    private String tPhoto;
    
    // Replace @JsonManagedReference with @JsonIgnoreProperties
    // This will ignore courses when serializing Teacher to prevent circular reference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"teacher"}) // This prevents infinite recursion
    private List<Course> courses;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "T_Qualification",
            joinColumns = @JoinColumn(name = "T_ID"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"T_ID" , "qualification"})
    )
    @Column(name = "qualification")
    private Set<String> qualification = new HashSet<>();

    // Getters and Setters
    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

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
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<String> getQualification() {
        return qualification;
    }

    public void setQualification(Set<String> qualification) {
        this.qualification = qualification;
    }

    public Object map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}