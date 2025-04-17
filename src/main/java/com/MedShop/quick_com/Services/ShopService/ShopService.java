package com.MedShop.quick_com.Services.ShopService;

import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Shop;

import java.util.List;

public interface ShopService {
    public Shop addShop(String name, String address, String GSTIN) throws Exception;
    public List<Customer> getConnectedCustomers(String shop_name);
}
