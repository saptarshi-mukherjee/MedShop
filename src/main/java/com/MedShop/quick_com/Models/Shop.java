package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String shop_name;
    private String shop_address;
    private String uid;
    private String GSTIN;
    @OneToMany(mappedBy = "shop")
    @JsonManagedReference
    private List<Employee> employees;
    @ManyToMany(mappedBy = "shops")
    @JsonManagedReference
    private List<Customer> customers;
    @OneToMany(mappedBy = "shop")
    @JsonManagedReference
    private List<Item> items;


    public Shop() {
        employees=new ArrayList<>();
        customers=new ArrayList<>();
        items=new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
