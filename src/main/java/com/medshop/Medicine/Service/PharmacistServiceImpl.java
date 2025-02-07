package com.medshop.Medicine.Service;


import com.medshop.Medicine.Models.Pharmacist;
import com.medshop.Medicine.Models.RefreshToken;
import com.medshop.Medicine.Observer.UserRegistrationPublisher;
import com.medshop.Medicine.Repositories.PharmacistRepository;
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
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    UserRegistrationPublisher publisher;
    @Autowired
    PharmacistRepository pharma_repo;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RefreshTokenService refresh_token_service;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    @Qualifier("jwt")
    TokenService token_service;

    @Override
    public List<Pharmacist> registerPharmacist(String username, String password, String full_name, String role, String college) {
        Pharmacist pharmacist=Pharmacist
                .getBuilder()
                .setUsername(username)
                .setPassword(encoder.encode(password))
                .setFull_name(full_name)
                .setRole(role)
                .setCreated_at()
                .setCollege(college)
                .build();
        publisher.execute(pharmacist);
        return getAllPharmacists();
    }

    @Override
    public List<Pharmacist> getAllPharmacists() {
        return pharma_repo.fetchAllPharmacists();
    }

    @Override
    public String verify(String username, String password) {
        Authentication authentication= manager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );
        boolean is_authorised=verifyRole(authentication);
        if(authentication.isAuthenticated() && is_authorised) {
            String access_token=token_service.getAccessToken(username,List.of("ROLE_PHARMACIST"));
            String refresh_token=token_service.getRefreshToken(username, List.of("ROLE_PHARMACIST"));
            RefreshToken refresh_token_obj=refresh_token_service.getRefreshToken(username);
            if(refresh_token_obj==null) {
                RefreshToken token=new RefreshToken();
                token.setUsername(username);
                token.setRefresh_token(refresh_token);
                refresh_token_service.saveRefreshToken(token);
            }
            else {
                refresh_token_obj.setRefresh_token(refresh_token);
                refresh_token_service.saveRefreshToken(refresh_token_obj);
            }
            return access_token;
        }
        else
            return "Authentication failure";
    }

    private boolean verifyRole(Authentication authentication) {
        List<String> roles=new ArrayList<>();
        boolean is_authorised=false;
        for(GrantedAuthority auth : authentication.getAuthorities())
            roles.add(auth.getAuthority());
        for(String role : roles) {
            if(role.equals("ROLE_PHARMACIST")) {
                is_authorised=true;
                break;
            }
        }
        return is_authorised;
    }

    @Override
    public String logout(String username) {
        RefreshToken token=refresh_token_service.getRefreshToken(username);
        refresh_token_service.deleteRefreshToken(token);
        return "Logged out";
    }

    @Override
    public Pharmacist getOnePharmacist(String username) {
        return pharma_repo.fetchOnePharmacist(username);
    }
}
