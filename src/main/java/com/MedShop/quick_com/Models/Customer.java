package com.MedShop.quick_com.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "customers")
public class Customer extends User {
    @ManyToMany
    @JsonBackReference
    private List<Shop> shops;


    public Customer() {
        shops=new ArrayList<>();
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
