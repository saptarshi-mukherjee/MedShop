package com.medshop.Medicine.Observer;

import com.medshop.Medicine.Models.Customer;
import com.medshop.Medicine.Models.Pharmacist;
import com.medshop.Medicine.Models.User;
import com.medshop.Medicine.Repositories.CustomerRepo;
import com.medshop.Medicine.Repositories.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistration implements EventHandler {

    @Autowired
    CustomerRepo customer_repo;
    @Autowired
    PharmacistRepository pharma_repo;

    @Override
    public void handle(User user) {
        if(user instanceof Customer) {
            Customer customer=(Customer) user;
            customer_repo.save(customer);
        }
        else {
            Pharmacist pharmacist=(Pharmacist) user;
            pharma_repo.save(pharmacist);
        }
    }
}
