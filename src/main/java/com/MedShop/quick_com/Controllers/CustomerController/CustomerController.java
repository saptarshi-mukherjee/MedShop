package com.MedShop.quick_com.Controllers.CustomerController;


import com.MedShop.quick_com.DTO.Requests.NewCustomerRequestDto;
import com.MedShop.quick_com.DTO.Responses.NewCustomerResponseDto;
import com.MedShop.quick_com.Models.Customer;
import com.MedShop.quick_com.Services.CustomerService.CustomerService;
import com.MedShop.quick_com.Strategies.StringNormalization.BasicNormalization;
import com.MedShop.quick_com.Strategies.StringNormalization.StringNormalizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
