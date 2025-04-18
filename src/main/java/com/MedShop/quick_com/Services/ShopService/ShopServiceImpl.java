package com.MedShop.quick_com.Services.ShopService;


import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Repositories.*;
import com.MedShop.quick_com.Strategies.MedicineAdditionStrategy.CheckAndInsertStrategy;
import com.MedShop.quick_com.Strategies.MedicineAdditionStrategy.MedicineAdditionStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shop_repo;
    @Autowired
    CustomerRepository customer_repo;
    @Autowired
    MedicineRepository medicine_repo;
    @Autowired
    BatchRepository batch_repo;
    @Autowired
    CompoundRepository compound_repo;
    @Autowired
    CompanyRepository company_repo;
    @Autowired
    ItemRepository item_repo;

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



    @Override
    public List<Customer> getConnectedCustomers(String shop_name) {
        Shop shop=shop_repo.fetchShopByName(shop_name);
        return shop.getCustomers();
    }

    @Override
    public Item insertMedicine(String medicine_name, int quantity, String unit, List<String> compound_names, String batch_number, String mfg_month, int mfg_year,
                               String exp_month, int exp_year, double price, String company_name, String lic_no, String shop_name, int stock) {
        MedicineAdditionStrategy strategy=new CheckAndInsertStrategy(medicine_repo,batch_repo,compound_repo,company_repo,item_repo,shop_repo);
       Item item= strategy.addMedicine(medicine_name,quantity,unit,compound_names,batch_number,mfg_month,mfg_year,
               exp_month,exp_year,price,company_name,lic_no,shop_name,stock);
       return item;
    }
}
