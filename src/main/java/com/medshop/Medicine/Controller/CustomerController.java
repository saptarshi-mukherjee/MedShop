package com.medshop.Medicine.Controller;


import com.medshop.Medicine.DTO.CustomerLoginDto;
import com.medshop.Medicine.DTO.UserRegistrationDto;
import com.medshop.Medicine.Models.Customer;
import com.medshop.Medicine.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerService customer_service;

    private String getUsername() {
        UserDetails principal= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }


    @PostMapping("/register")
    public List<Customer> register(@RequestBody UserRegistrationDto register) {
        return customer_service.registerCustomer(register.getUsername(), register.getPassword(),
                register.getFull_name(), register.getRole(), register.getCashback());

    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerLoginDto login) {
        System.out.println("In login controller");
        return customer_service.verify(login.getUsername(),login.getPassword());
    }

    @GetMapping("/logout")
    public String logout() {
        String username=getUsername();
        return customer_service.logout(username);
    }

    @GetMapping("/get/one")
    public Customer getCustomer() {
        String username=getUsername();
        return customer_service.getOneCustomer(username);
    }

}
