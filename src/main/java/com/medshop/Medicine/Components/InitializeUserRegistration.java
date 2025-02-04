package com.medshop.Medicine.Components;


import com.medshop.Medicine.Observer.UserInfoRegistration;
import com.medshop.Medicine.Observer.UserRegistration;
import com.medshop.Medicine.Observer.UserRegistrationPublisher;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"userRegistration", "userInfoRegistration", "userRegistrationPublisher"})
public class InitializeUserRegistration {

    UserRegistrationPublisher publisher;
    UserRegistration user_registration;
    UserInfoRegistration info_registration;

    public InitializeUserRegistration(UserRegistrationPublisher publisher, UserRegistration user_registration, UserInfoRegistration info_registration) {
        this.publisher = publisher;
        this.user_registration = user_registration;
        this.info_registration = info_registration;
    }

    @PostConstruct
    public void init() {
        publisher.register(user_registration);
        publisher.register(info_registration);
    }
}
