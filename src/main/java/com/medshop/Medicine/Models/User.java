package com.medshop.Medicine.Models;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String username, password, full_name, role;
    private LocalDateTime created_at;

    public User() {
        //For hibernate
    }

    public User(String username, String password, String full_name, String role, LocalDateTime created_at) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.role = role;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
