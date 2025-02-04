package com.medshop.Medicine.Repositories;


import com.medshop.Medicine.Models.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist,Long> {

    @Query(value = "select * from pharmacists", nativeQuery = true)
    public List<Pharmacist> fetchAllPharmacists();
}
