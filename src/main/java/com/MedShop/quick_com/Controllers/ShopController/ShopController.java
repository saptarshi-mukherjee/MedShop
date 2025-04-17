package com.MedShop.quick_com.Controllers.ShopController;


import com.MedShop.quick_com.DTO.Requests.ShopRequestDto;
import com.MedShop.quick_com.DTO.Responses.ConnectedCustomersResponseDto;
import com.MedShop.quick_com.DTO.Responses.CustomerResponseDto;
import com.MedShop.quick_com.Models.Customer;
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
}
