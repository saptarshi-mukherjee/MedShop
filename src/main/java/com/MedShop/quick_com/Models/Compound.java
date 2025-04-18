package com.MedShop.quick_com.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "compounds")
public class Compound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String compound_name;
    @ManyToMany(mappedBy = "compounds")
    @JsonManagedReference
    private List<Medicine> medicines;


    public Compound() {
        medicines=new ArrayList<>();
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompound_name() {
        return compound_name;
    }

    public void setCompound_name(String compound_name) {
        this.compound_name = compound_name;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o)
            return true;
        if(!(o instanceof Compound))
            return false;
        Compound compound=(Compound) o;
        if(this.compound_name.equals(compound.getCompound_name()))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode(){
        if(this.compound_name!=null)
            return this.compound_name.hashCode();
        else
            return 0;
    }
}
