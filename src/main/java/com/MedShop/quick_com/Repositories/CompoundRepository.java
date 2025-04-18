package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompoundRepository extends JpaRepository<Compound,Long> {


    @Query(value = "select * from compounds where compound_name = :compound_name", nativeQuery = true)
    public Compound fetchByCompoundName(@Param("compound_name") String compound_name);
}
