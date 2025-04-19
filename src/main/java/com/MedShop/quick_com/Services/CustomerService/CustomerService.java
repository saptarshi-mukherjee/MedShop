package com.MedShop.quick_com.Services.CustomerService;

import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Medicine;
import com.MedShop.quick_com.Models.Shop;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(String name, String address, String email, Long phone);
    public Customer connectShops(String customer_name, String uid);
    public List<Shop> getAllShops(String customer_name);
    public List<Item> searchMedicine(String shop_name, String medicine_name);
    public List<Item> searchCompound(String shop_name, String compound_name);
}
