package com.medshop.Medicine.Observer;

import com.medshop.Medicine.Models.User;
import com.medshop.Medicine.Models.UserInfo;
import com.medshop.Medicine.Repositories.UserInfoRepository;
import com.medshop.Medicine.Utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoRegistration implements EventHandler {

    @Autowired
    UserInfoRepository info_repo;

    @Override
    public void handle(User user) {
        UserInfo user_info=new UserInfo();
        user_info.setUsername(user.getUsername());
        user_info.setPassword(user.getPassword());
        user_info.setRole_type(RoleType.valueOf(user.getRole()));
        info_repo.save(user_info);
    }
}
