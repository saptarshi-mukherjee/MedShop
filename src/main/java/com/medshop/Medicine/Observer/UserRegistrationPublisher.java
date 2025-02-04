package com.medshop.Medicine.Observer;


import com.medshop.Medicine.Models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Scope("singleton")
public class UserRegistrationPublisher {
    private List<EventHandler> events;
    private static UserRegistrationPublisher instance=null;

    private UserRegistrationPublisher() {
        events=new ArrayList<>();
    }

    public static UserRegistrationPublisher getInstance() {
        if(instance==null)
            instance=new UserRegistrationPublisher();
        return instance;
    }

    public void register(EventHandler event) {
        events.add(event);
    }

    public void cancel(EventHandler event) {
        events.remove(event);
    }

    public void execute(User user) {
        for(EventHandler event : events)
            event.handle(user);
    }

}
