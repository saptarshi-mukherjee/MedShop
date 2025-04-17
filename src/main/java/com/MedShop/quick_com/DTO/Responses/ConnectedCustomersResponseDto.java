package com.MedShop.quick_com.DTO.Responses;

import java.util.List;

public class ConnectedCustomersResponseDto {
    private String shop_name;
    private List<CustomerResponseDto> customers;


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public List<CustomerResponseDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerResponseDto> customers) {
        this.customers = customers;
    }
}
