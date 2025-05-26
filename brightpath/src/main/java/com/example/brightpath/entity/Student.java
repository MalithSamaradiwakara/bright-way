package com.example.brightpath.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "S_NAME")
    private String name;

    @Column(name = "S_EMAIL")
    private String email;

    @Column(name = "S_PHONE")
    private String phone;

    @Column(name = "S_ADDRESS")
    private String address;

    @Column(name = "S_PHOTO")
    private String photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOGIN_ID")
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
