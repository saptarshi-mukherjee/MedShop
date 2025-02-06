package com.medshop.Medicine.Service;


import com.medshop.Medicine.Models.Customer;
import com.medshop.Medicine.Models.RefreshToken;
import com.medshop.Medicine.Observer.UserRegistrationPublisher;
import com.medshop.Medicine.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    UserRegistrationPublisher publisher;
    @Autowired
    CustomerRepo customer_repo;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    @Qualifier("jwt")
    TokenService token_service;
    @Autowired
    RefreshTokenService refresh_token_service;

    @Override
    public List<Customer> registerCustomer(String username, String password, String full_name, String role, Double cashback) {
        Customer customer=Customer
                .getBuilder()
                .setUsername(username)
                .setPassword(encoder.encode(password))
                .setFull_name(full_name)
                .setRole(role)
                .setCashback(cashback)
                .setCreated_at()
                .build();
        publisher.execute(customer);
        return getAllCustomers();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customer_repo.fetchAllCustomers();
    }

    @Override
    public String verify(String username, String password) {
        System.out.println("In customer service");
        Authentication authentication=manager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );
        System.out.println("authentication object created");
        boolean is_authorised=verifyRole(authentication);
        if(authentication.isAuthenticated() && is_authorised) {
            System.out.println("customer authenticated");
            String access_token=token_service.getAccessToken(username, List.of("ROLE_CUSTOMER"));
            String refresh_token=token_service.getRefreshToken(username, List.of("ROLE_CUSTOMER"));
            RefreshToken refresh_token_obj= refresh_token_service.getRefreshToken(username);
            if(refresh_token_obj==null) {
                refresh_token_obj=new RefreshToken();
                refresh_token_obj.setUsername(username);
                refresh_token_obj.setRefresh_token(refresh_token);
                refresh_token_service.saveRefreshToken(refresh_token_obj);
            }
            else {
                refresh_token_obj.setRefresh_token(refresh_token);
                refresh_token_service.saveRefreshToken(refresh_token_obj);
            }
            return access_token;
        }
        else
            return "authentication failure";
    }

    private boolean verifyRole(Authentication authentication) {
        List<String> authorities=new ArrayList<>();
        boolean is_authorised=false;
        for(GrantedAuthority auth : authentication.getAuthorities())
            authorities.add(auth.getAuthority());
        for(String auth : authorities) {
            if(auth.equals("ROLE_CUSTOMER")) {
                is_authorised=true;
                break;
            }
        }
        return is_authorised;
    }


    public String logout(String username) {
        RefreshToken refresh_token=refresh_token_service.getRefreshToken(username);
        refresh_token_service.deleteRefreshToken(refresh_token);
        return "Logged out";
    }
}
