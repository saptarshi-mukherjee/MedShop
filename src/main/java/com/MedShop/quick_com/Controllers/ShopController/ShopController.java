package com.MedShop.quick_com.Controllers.ShopController;


import com.MedShop.quick_com.DTO.Requests.ShopRequestDto;
import com.MedShop.quick_com.Models.Shop;
import com.MedShop.quick_com.Services.ShopService.ShopService;
import com.MedShop.quick_com.Strategies.StringNormalization.BasicNormalization;
import com.MedShop.quick_com.Strategies.StringNormalization.StringNormalizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
