package com.medshop.Medicine.Service;

import com.medshop.Medicine.Models.Pharmacist;

import java.util.List;

public interface PharmacistService {
    public List<Pharmacist> registerPharmacist(String username, String password, String full_name, String role, String college);
    public List<Pharmacist> getAllPharmacists();
    public String verify(String username, String password);
    public String logout(String username);
}
