package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "batches")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String batch_number;
    private LocalDate mfg_date;
    private LocalDate exp_date;
    private Double price;
    private ExpiryStatus expiry_status;
    @ManyToOne
    @JsonBackReference
    private Medicine medicine;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public LocalDate getMfg_date() {
        return mfg_date;
    }

    public void setMfg_date(LocalDate mfg_date) {
        this.mfg_date = mfg_date;
    }

    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ExpiryStatus getExpiry_status() {
        return expiry_status;
    }

    public void setExpiry_status(ExpiryStatus expiry_status) {
        this.expiry_status = expiry_status;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o)
            return true;
        if(!(o instanceof Batch))
            return false;
        Batch batch=(Batch) o;
        if(this.batch_number.equals(batch.getBatch_number()))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        if (this.batch_number!=null)
            return this.batch_number.hashCode();
        else
            return 0;
    }
}
