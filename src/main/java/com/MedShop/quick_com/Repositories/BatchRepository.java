package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {

    @Query(value = "select * from batches where batch_number = :batch_number", nativeQuery = true)
    public Batch fetchByBatchNumber(@Param("batch_number") String batch_number);
}
