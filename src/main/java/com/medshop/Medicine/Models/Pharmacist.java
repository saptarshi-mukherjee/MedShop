package com.medshop.Medicine.Models;


import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity(name = "pharmacists")
public class Pharmacist extends User {
    private String college;

    public Pharmacist() {
        // for hibernate
    }

    private Pharmacist(Builder builder) {
        super(builder.username, builder.password, builder.full_name, builder.role, builder.created_at);
        this.college= builder.college;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public static class Builder {
        private String username, password, full_name, role;
        LocalDateTime created_at;
        private String college;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFull_name(String full_name) {
            this.full_name = full_name;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setCreated_at(LocalDateTime created_at) {
            this.created_at = created_at;
            return this;
        }

        public Builder setCollege(String college) {
            this.college = college;
            return this;
        }

        public Pharmacist build() {
            return new Pharmacist(this);
        }
    }


    public static Builder getBuilder() {
        return new Builder();
    }

}
