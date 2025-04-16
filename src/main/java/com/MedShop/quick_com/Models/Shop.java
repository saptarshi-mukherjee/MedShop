package com.MedShop.quick_com.Models;


import com.MedShop.quick_com.Strategies.UIDGeneration.AbbreviatedUID;
import com.MedShop.quick_com.Strategies.UIDGeneration.UIDGenerationStrategy;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "shops")
public class Shop {

    @Setter
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
    @Setter
    @OneToMany(mappedBy = "shop")
    @JsonManagedReference
    private List<Item> items;


    private Shop(ShopBuilder builder) {
        employees=new ArrayList<>();
        customers=new ArrayList<>();
        items=new ArrayList<>();
        this.shop_name= builder.shop_name;
        this.shop_address= builder.shop_address;
        this.GSTIN= builder.GSTIN;
    }

    public List<Item> getItems() {
        return items;
    }

    public long getId() {
        return id;
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

    public void setUid() {
        UIDGenerationStrategy strategy=new AbbreviatedUID();
        String uid=strategy.generateUid(this);
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



    public static class ShopBuilder {
        private String shop_name;
        private String shop_address;
        private String GSTIN;


        public ShopBuilder setShop_name(String shop_name) {
            this.shop_name = shop_name;
            return this;
        }

        public ShopBuilder setShop_address(String shop_address) {
            this.shop_address = shop_address;
            return this;
        }

        public ShopBuilder setGSTIN(String GSTIN) {
            this.GSTIN = GSTIN;
            return this;
        }

        public Shop build() throws Exception {
            if(shop_name==null || shop_address==null || GSTIN==null)
                throw new Exception("Mandatory fields missing");
            return new Shop(this);
        }
    }


    public static ShopBuilder getBuilder() {
        return new ShopBuilder();
    }
}
