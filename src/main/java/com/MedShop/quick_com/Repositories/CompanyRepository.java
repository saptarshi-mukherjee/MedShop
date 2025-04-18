package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Query(value = "select * from companies where company_name = :company_name", nativeQuery = true)
    public Company fetchByCompanyName(@Param("company_name") String company_name);
}
