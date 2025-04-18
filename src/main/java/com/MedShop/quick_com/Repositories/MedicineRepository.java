package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    @Query(value = "select * from medicines where medicine_name = :medicine_name", nativeQuery = true)
    public Medicine fetchByMedicineName(@Param("medicine_name") String medicine_name);
}
