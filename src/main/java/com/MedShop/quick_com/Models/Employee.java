package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name="employees")
public class Employee extends User {
    private String employee_id;
    @ManyToOne
    @JsonBackReference
    private Shop shop;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
