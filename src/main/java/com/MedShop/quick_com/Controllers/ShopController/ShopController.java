package com.MedShop.quick_com.Controllers.ShopController;


import com.MedShop.quick_com.DTO.Requests.AddMedicineRequestDto;
import com.MedShop.quick_com.DTO.Requests.ShopRequestDto;
import com.MedShop.quick_com.DTO.Responses.AddMedicineResponseDto;
import com.MedShop.quick_com.DTO.Responses.ConnectedCustomersResponseDto;
import com.MedShop.quick_com.DTO.Responses.CustomerResponseDto;
import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Services.ShopService.ShopService;
import com.MedShop.quick_com.Strategies.StringNormalization.BasicNormalization;
import com.MedShop.quick_com.Strategies.StringNormalization.StringNormalizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shop_service;
    StringNormalizationStrategy strategy=new BasicNormalization();

    @PostMapping("/register")
    public Shop registerShop(@RequestBody ShopRequestDto request) {
        String name=strategy.normalize(request.getName());
        String address=strategy.normalize(request.getAddress());
        String GSTIN=request.getGstin();
        Shop shop=null;
        try {
            shop=shop_service.addShop(name, address, GSTIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shop;
    }


    @GetMapping("/get/all/customer/{shop_name}")
    public ConnectedCustomersResponseDto getConnectedCustomers(@PathVariable("shop_name") String shop_name) {
        List<Customer> customers=shop_service.getConnectedCustomers(shop_name);
        List<CustomerResponseDto> customer_response_list=new ArrayList<>();
        for(Customer customer : customers) {
            CustomerResponseDto customer_response=new CustomerResponseDto();
            customer_response.setName(customer.getName());
            customer_response.setAddress(customer.getAddress());
            customer_response_list.add(customer_response);
        }
        ConnectedCustomersResponseDto response=new ConnectedCustomersResponseDto();
        response.setShop_name(shop_name);
        response.setCustomers(customer_response_list);
        return response;
    }


    @PostMapping("/add/med")
    public AddMedicineResponseDto addMedicine(@RequestBody AddMedicineRequestDto request) {
        String medicine_name= request.getMedicine_name();
        int quantity= request.getQuantity();
        String unit= request.getUnit();
        List<String> compound_names=request.getCompound_names();
        String batch_number= request.getBatch_number();
        String mfg_month= request.getMfg_month();
        int mfg_year= request.getMfg_year();
        String exp_month= request.getExp_month();
        int exp_year= request.getExp_year();
        double price= request.getPrice();
        String company_name= request.getCompany_name();
        String lic_no= request.getLic_no();
        String shop_name= request.getShop_name();
        int stock= request.getStock();
        Item item=shop_service.insertMedicine(medicine_name,quantity,unit,compound_names,batch_number,mfg_month,mfg_year,
                exp_month,exp_year,price,company_name,lic_no,shop_name,stock);
        AddMedicineResponseDto response=new AddMedicineResponseDto();
        response.setItem_id(item.getId());
        response.setMedicine_name(item.getMedicine().getMedicine_name());
        response.setBatch_number(item.getBatch().getBatch_number());
        response.setShop_name(item.getShop().getShop_name());
        response.setStock(item.getQty_in_stock());
        return response;
    }
}
