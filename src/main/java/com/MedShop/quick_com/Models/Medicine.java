package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String medicine_name;
    private Integer quantity;
    private Unit unit;
    @OneToMany(mappedBy = "medicine")
    @JsonManagedReference
    private List<Batch> batches;
    @ManyToMany
    @JsonBackReference
    private List<Compound> compounds;
    @ManyToOne
    @JsonBackReference
    private Company company;


    public Medicine() {
        batches=new ArrayList<>();
        compounds=new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public List<Compound> getCompounds() {
        return compounds;
    }

    public void setCompounds(List<Compound> compounds) {
        this.compounds = compounds;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
