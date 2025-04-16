package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Medicine medicine;
    @ManyToOne
    @JsonBackReference
    private Shop shop;
    @ManyToOne
    private Batch batch;
    private Integer qty_in_stock;

    public Integer getQty_in_stock() {
        return qty_in_stock;
    }

    public void setQty_in_stock(Integer qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
