package com.MedShop.quick_com.Services.ShopService;

import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Shop;

import java.util.List;

public interface ShopService {
    public Shop addShop(String name, String address, String GSTIN) throws Exception;
    public List<Customer> getConnectedCustomers(String shop_name);
    public Item insertMedicine(String medicine_name, int quantity, String unit, List<String> compound_names, String batch_number, String mfg_month, int mfg_year,
                               String exp_month, int exp_year, double price, String company_name, String lic_no, String shop_name, int stock);
}
