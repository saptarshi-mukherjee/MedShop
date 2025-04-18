package com.MedShop.quick_com.DTO.Requests;

import java.util.List;

public class AddMedicineRequestDto {
    private String medicine_name;
    private int quantity;
    private String unit;
    private List<String> compound_names;
    private String batch_number;
    private String mfg_month;
    private int mfg_year;
    private String exp_month;
    private int exp_year;
    private double price;
    private String company_name;
    private String lic_no;
    private String shop_name;
    private int stock;


    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getCompound_names() {
        return compound_names;
    }

    public void setCompound_names(List<String> compound_names) {
        this.compound_names = compound_names;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getMfg_month() {
        return mfg_month;
    }

    public void setMfg_month(String mfg_month) {
        this.mfg_month = mfg_month;
    }

    public int getMfg_year() {
        return mfg_year;
    }

    public void setMfg_year(int mfg_year) {
        this.mfg_year = mfg_year;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLic_no() {
        return lic_no;
    }

    public void setLic_no(String lic_no) {
        this.lic_no = lic_no;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
