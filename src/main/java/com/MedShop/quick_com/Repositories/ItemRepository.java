package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {


    @Query(value = "select * from items where shop_id = :shop_id and medicine_id = :medicine_id\n" +
            "and batch_id = :batch_id", nativeQuery = true)
    public Item getItemInShop(@Param("shop_id") long shop_id, @Param("medicine_id") long medicine_id,
                              @Param("batch_id") long batch_id);


    @Query(value = "select * from items where shop_id = :shop_id and medicine_id = :medicine_id", nativeQuery = true)
    public List<Item> fetchItemByShopAndMedicineId(@Param("shop_id") long shop_id, @Param("medicine_id") long medicine_id);


    @Query(value = "select * from items where shop_id = :shop_id", nativeQuery = true)
    public List<Item> fetchByShopId(@Param("shop_id") long shop_id);

}
