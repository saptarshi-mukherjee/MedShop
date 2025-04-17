package com.MedShop.quick_com.Services.CustomerService;


import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Repositories.CustomerRepository;
import com.MedShop.quick_com.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customer_repo;
    @Autowired
    ShopRepository shop_repo;

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
}
