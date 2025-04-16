package com.MedShop.quick_com.Strategies.UIDGeneration;

import com.MedShop.quick_com.Models.Shop;

public class AbbreviatedUID implements UIDGenerationStrategy {
    @Override
    public String generateUid(Shop shop) {
        long id=shop.getId();
        String name= shop.getShop_name();
        StringBuilder uid=new StringBuilder("");
        int n=name.length(), i;
        for(i=0;i<n;i++) {
            if(i==0 || name.charAt(i-1)==' ')
                uid.append(name.charAt(i));
        }
        uid.append(Long.toString(id));
        return uid.toString();
    }
}
