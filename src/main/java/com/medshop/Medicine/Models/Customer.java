package com.medshop.Medicine.Models;


import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity(name = "customers")
public class Customer extends User {
    private Double cashback;
    public Customer() {
        // For hibernate
    }

    private Customer(Builder builder) {
        super(builder.username, builder.password, builder.full_name, builder.role,builder.created_at);
        this.cashback=builder.cashback;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }


    public static class Builder {
        String username, password, full_name, role;
        LocalDateTime created_at;
        Double cashback;

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

        public Builder setCreated_at() {
            this.created_at = LocalDateTime.now();
            return this;
        }

        public Builder setCashback(Double cashback) {
            this.cashback = cashback;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }
}
