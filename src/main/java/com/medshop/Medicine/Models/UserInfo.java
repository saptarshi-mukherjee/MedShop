package com.medshop.Medicine.Models;


import com.medshop.Medicine.Utils.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_info_id;
    private String username, password;
    private RoleType role_type;

    public Long getUser_info_id() {
        return user_info_id;
    }

    public void setUser_info_id(Long user_info_id) {
        this.user_info_id = user_info_id;
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

    public RoleType getRole_type() {
        return role_type;
    }

    public void setRole_type(RoleType role_type) {
        this.role_type = role_type;
    }
}
