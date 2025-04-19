package com.MedShop.quick_com.Controllers.CustomerController;


import com.MedShop.quick_com.DTO.Requests.ConnectShopRequestDto;
import com.MedShop.quick_com.DTO.Requests.NewCustomerRequestDto;
import com.MedShop.quick_com.DTO.Responses.ConnectedShopsResponseDto;
import com.MedShop.quick_com.DTO.Responses.NewCustomerResponseDto;
import com.MedShop.quick_com.DTO.Responses.SearchMedicineResponseDto;
import com.MedShop.quick_com.DTO.Responses.ShopResponseDto;
import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Models.ExpiryStatus;
import com.MedShop.quick_com.Models.Item;
import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Services.CustomerService.CustomerService;
import com.MedShop.quick_com.Strategies.StringNormalization.BasicNormalization;
import com.MedShop.quick_com.Strategies.StringNormalization.StringNormalizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customer_service;
    StringNormalizationStrategy strategy=new BasicNormalization();

    @PostMapping("/register")
    public NewCustomerResponseDto registerCustomer(@RequestBody NewCustomerRequestDto request) {
        String name=strategy.normalize(request.getName());
        String address=strategy.normalize(request.getAddress());
        String email=request.getEmail();
        Long phone= request.getPhone();
        Customer customer=customer_service.addCustomer(name,address,email,phone);
        NewCustomerResponseDto response=new NewCustomerResponseDto();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setAddress(customer.getAddress());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        return response;
    }


    @PutMapping("/connect")
    public ConnectedShopsResponseDto connect(@RequestBody ConnectShopRequestDto request) {
        Customer customer=customer_service.connectShops(request.getCustomer_name(), request.getUid());
        List<Shop> shops=customer.getShops();
        List<ShopResponseDto> connected_shops=new ArrayList<>();
        for(Shop shop : shops) {
            ShopResponseDto shop_response=new ShopResponseDto();
            shop_response.setShop_name(shop.getShop_name());
            shop_response.setShop_address(shop.getShop_address());
            shop_response.setShop_uid(shop.getUid());
            connected_shops.add(shop_response);
        }
        ConnectedShopsResponseDto response=new ConnectedShopsResponseDto();
        response.setCustomer_name(customer.getName());
        response.setShops(connected_shops);
        return response;
    }


    @GetMapping("/get/all/shops/{customer_name}")
    public ConnectedShopsResponseDto getAllShops(@PathVariable("customer_name") String customer_name) {
        List<Shop> shops=customer_service.getAllShops(customer_name);
        List<ShopResponseDto> connected_shops=new ArrayList<>();
        for(Shop shop : shops) {
            ShopResponseDto shop_response=new ShopResponseDto();
            shop_response.setShop_name(shop.getShop_name());
            shop_response.setShop_address(shop.getShop_address());
            shop_response.setShop_uid(shop.getUid());
            connected_shops.add(shop_response);
        }
        ConnectedShopsResponseDto response=new ConnectedShopsResponseDto();
        response.setCustomer_name(customer_name);
        response.setShops(connected_shops);
        return response;
    }


    @GetMapping("/get")
    public List<SearchMedicineResponseDto> searchMedicine(@RequestParam("shop") String shop_name,
                                                    @RequestParam("med") String medicine_name) {
        List<Item> items=customer_service.searchMedicine(shop_name,medicine_name);
        List<SearchMedicineResponseDto> results=new ArrayList<>();
        for(Item item : items) {
            if(item.getBatch().getExpiry_status()== ExpiryStatus.NOT_EXPIRED) {
                SearchMedicineResponseDto response = new SearchMedicineResponseDto();
                response.setMedicine_name(item.getMedicine().getMedicine_name());
                response.setBatch_number(item.getBatch().getBatch_number());
                response.setExp_date(item.getBatch().getExp_date().toString());
                response.setPrice(item.getBatch().getPrice());
                results.add(response);
            }
        }
        return results;
    }


    @GetMapping("/get/compound")
    public List<SearchMedicineResponseDto> searchCompound(@RequestParam("shop") String shop_name,
                                                          @RequestParam("compound") String compound_name) {
        List<Item> items=customer_service.searchCompound(shop_name,compound_name);
        List<SearchMedicineResponseDto> response_list=new ArrayList<>();
        for(Item item : items) {
            SearchMedicineResponseDto response=new SearchMedicineResponseDto();
            response.setMedicine_name(item.getMedicine().getMedicine_name());
            response.setBatch_number(item.getBatch().getBatch_number());
            response.setExp_date(item.getBatch().getExp_date().toString());
            response.setPrice(item.getBatch().getPrice());
            response_list.add(response);
        }
        return response_list;
    }
}
