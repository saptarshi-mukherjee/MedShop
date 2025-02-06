package com.medshop.Medicine.Service;

import com.medshop.Medicine.Models.CustomUserDetails;
import com.medshop.Medicine.Models.UserInfo;
import com.medshop.Medicine.Repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoRepository user_info_repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user_info=user_info_repo.fetchByUsername(username);
        return new CustomUserDetails(user_info);
    }
}
