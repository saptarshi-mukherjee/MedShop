package com.MedShop.quick_com.DTO.Responses;

import java.util.List;

public class ConnectedShopsResponseDto {
    private String customer_name;
    private List<ShopResponseDto> shops;


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public List<ShopResponseDto> getShops() {
        return shops;
    }

    public void setShops(List<ShopResponseDto> shops) {
        this.shops = shops;
    }
}
