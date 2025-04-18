package com.MedShop.quick_com.Strategies.MedicineAdditionStrategy;

import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Unit;

import java.util.List;

public interface MedicineAdditionStrategy {
    public Item addMedicine(String medicine_name, int quantity, String unit, List<String> compounds,
                            String batch_number, String mfg_month, int mfg_year, String exp_month, int exp_year,
                            double price, String company_name, String lic_no, String shop_name, int stock);
}
