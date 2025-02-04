package com.medshop.Medicine.Controller;


import com.medshop.Medicine.DTO.UserRegistrationDto;
import com.medshop.Medicine.Models.Customer;
import com.medshop.Medicine.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customer_service;


    @PostMapping("/register")
    public List<Customer> register(@RequestBody UserRegistrationDto register) {
        return customer_service.registerCustomer(register.getUsername(), register.getPassword(),
                register.getFull_name(), register.getRole(), register.getCashback());

    }
}
