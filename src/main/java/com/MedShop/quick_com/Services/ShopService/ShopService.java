package com.MedShop.quick_com.Services.ShopService;

import com.MedShop.quick_com.Models.Shop;

public interface ShopService {
    public Shop addShop(String name, String address, String GSTIN) throws Exception;
}
