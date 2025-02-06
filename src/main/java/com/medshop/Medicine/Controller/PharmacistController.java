package com.medshop.Medicine.Controller;


import com.medshop.Medicine.DTO.PharmacistLoginDto;
import com.medshop.Medicine.DTO.PharmacistRegistrationDto;
import com.medshop.Medicine.Models.Pharmacist;
import com.medshop.Medicine.Service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String login(@RequestBody PharmacistLoginDto login) {
        return pharma_service.verify(login.getUsername(),login.getPassword());
    }

    @GetMapping("/logout/{username}")
    public String logout(@PathVariable("username") String username) {
        return pharma_service.logout(username);
    }
}
