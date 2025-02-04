package com.medshop.Medicine.DTO;


public class UserRegistrationDto {
    String username, password, full_name, role;
    Double cashback;

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

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }
}
