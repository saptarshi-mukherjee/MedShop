package com.medshop.Medicine.Service;


import com.medshop.Medicine.Models.Customer;
import com.medshop.Medicine.Observer.UserRegistrationPublisher;
import com.medshop.Medicine.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    UserRegistrationPublisher publisher;
    @Autowired
    CustomerRepo customer_repo;

    @Override
    public List<Customer> registerCustomer(String username, String password, String full_name, String role, Double cashback) {
        Customer customer=Customer
                .getBuilder()
                .setUsername(username)
                .setPassword(password)
                .setFull_name(full_name)
                .setRole(role)
                .setCashback(cashback)
                .setCreated_at()
                .build();
        publisher.execute(customer);
        return getAllCustomers();
    }

    public List<Customer> getAllCustomers() {
        return customer_repo.fetchAllCustomers();
    }
}
