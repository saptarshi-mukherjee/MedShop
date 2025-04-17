package com.MedShop.quick_com.Services.CustomerService;

import com.MedShop.quick_com.Models.Customer;

public interface CustomerService {
    public Customer addCustomer(String name, String address, String email, Long phone);
    public Customer connectShops(String customer_name, String uid);
}
