package com.MedShop.quick_com.Services.CustomerService;


import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customer_repo;

    @Override
    public Customer addCustomer(String name, String address, String email, Long phone) {
        Customer customer=new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer=customer_repo.save(customer);
        return customer;
    }
}
