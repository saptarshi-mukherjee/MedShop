package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    @Query(value = "select * from customers where name = :name", nativeQuery = true)
    public Customer fetchByName(@Param("name") String name);

    @Query(value = "select * from customers where name = :name", nativeQuery = true)
    public Customer fetchShopsByName(@Param("name") String name);
}
