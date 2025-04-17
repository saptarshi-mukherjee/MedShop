package com.MedShop.quick_com.Repositories;


import com.MedShop.quick_com.Models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {


    @Query(value = "select * from shops where uid = :uid", nativeQuery = true)
    public Shop fetchShopByUid(@Param("uid") String uid);
}
