package com.example.brightpath.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID")
    private Long id;

    @Column(name = "S_Name", nullable = false)
    private String name;

    @Column(name = "S_Email", unique = true, nullable = false)
    private String email;

    @Column(name = "S_Contact", nullable = false)
    private String contact;

    @Column(name = "S_Photo", unique = true, nullable = false)
    private String photo;

    @JsonIgnore
    @OneToOne(mappedBy = "student")
    private Login login;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
