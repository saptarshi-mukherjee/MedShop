package com.medshop.Medicine.Repositories;


import com.medshop.Medicine.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query(value = "select * from customers", nativeQuery = true)
    public List<Customer> fetchAllCustomers();
}
