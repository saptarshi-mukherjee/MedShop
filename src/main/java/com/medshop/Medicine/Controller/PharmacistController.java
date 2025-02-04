package com.medshop.Medicine.Controller;


import com.medshop.Medicine.DTO.PharmacistRegistrationDto;
import com.medshop.Medicine.Models.Pharmacist;
import com.medshop.Medicine.Service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {

    @Autowired
    PharmacistService pharma_service;

    @PostMapping("/register")
    public List<Pharmacist> registerPharmacist(@RequestBody PharmacistRegistrationDto register) {
        return pharma_service.registerPharmacist(register.getUsername(), register.getPassword(), register.getFull_name(),
                register.getRole(), register.getCollege());
    }
}
