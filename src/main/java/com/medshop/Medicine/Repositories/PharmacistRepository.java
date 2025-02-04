package com.medshop.Medicine.Repositories;


import com.medshop.Medicine.Models.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist,Long> {

}
