package com.MedShop.quick_com.Services.CustomerService;


import com.MedShop.quick_com.Models.*;
import com.MedShop.quick_com.Repositories.CustomerRepository;
import com.MedShop.quick_com.Repositories.ItemRepository;
import com.MedShop.quick_com.Repositories.MedicineRepository;
import com.MedShop.quick_com.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customer_repo;
    @Autowired
    ShopRepository shop_repo;
    @Autowired
    MedicineRepository medicine_repo;
    @Autowired
    ItemRepository item_repo;

    @Override
    public Customer addCustomer(String name, String address, String email, Long phone) {
        Customer customer=new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer=customer_repo.save(customer);
        return customer;
    }

    @Override
    public Customer connectShops(String customer_name, String uid) {
        Customer customer=customer_repo.fetchByName(customer_name);
        Shop shop=shop_repo.fetchShopByUid(uid);
        customer.getShops().add(shop);
        shop.getCustomers().add(customer);
        customer=customer_repo.save(customer);
        shop=shop_repo.save(shop);
        return customer;
    }

    @Override
    public List<Shop> getAllShops(String customer_name) {
        Customer customer=customer_repo.fetchShopsByName(customer_name);
        return customer.getShops();
    }

    @Override
    public List<Item> searchMedicine(String shop_name, String medicine_name) {
        Medicine medicine=medicine_repo.fetchByMedicineName(medicine_name);
        Shop shop=shop_repo.fetchShopByName(shop_name);
        long medicine_id= medicine.getId();
        long shop_id=shop.getId();
        List<Item> medicines=item_repo.fetchItemByShopAndMedicineId(shop_id,medicine_id);
        return medicines;
    }

    @Override
    public List<Item> searchCompound(String shop_name, String compound_name) {
        Shop shop=shop_repo.fetchShopByName(shop_name);
        List<Item> items=item_repo.fetchByShopId(shop.getId());
        List<Item> required_items=new ArrayList<>();
        label:
        for(Item item : items) {
            List<Compound> compounds=item.getMedicine().getCompounds();
            for(Compound compound : compounds) {
                if(compound.getCompound_name().equals(compound_name) && item.getBatch().getExpiry_status()==ExpiryStatus.NOT_EXPIRED) {
                    required_items.add(item);
                    continue label;
                }
            }
        }
        return required_items;
    }
}
