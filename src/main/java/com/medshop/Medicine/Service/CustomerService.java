package com.medshop.Medicine.Service;

import com.medshop.Medicine.Models.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> registerCustomer(String username, String password, String full_name, String role, Double cashback);

    public List<Customer> getAllCustomers();

    public String verify(String username, String password);

    public String logout(String username);
}
