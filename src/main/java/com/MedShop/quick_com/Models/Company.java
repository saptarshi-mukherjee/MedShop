package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lic_no;
    private String company_name;
    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<Medicine> medicines;


    public Company() {
        medicines=new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLic_no() {
        return lic_no;
    }

    public void setLic_no(String lic_no) {
        this.lic_no = lic_no;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
