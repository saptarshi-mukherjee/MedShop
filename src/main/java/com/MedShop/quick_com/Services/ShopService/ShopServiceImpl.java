package com.MedShop.quick_com.Services.ShopService;


import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shop_repo;

    @Override
    public Shop addShop(String name, String address, String GSTIN) throws Exception {
        Shop shop=Shop.getBuilder()
                .setShop_name(name)
                .setShop_address(address)
                .setGSTIN(GSTIN)
                .build();
        shop=shop_repo.save(shop);
        shop.setUid();
        shop=shop_repo.save(shop);
        return shop;
    }
}
