package com.medshop.Medicine.Observer;

import com.medshop.Medicine.Models.User;

public interface EventHandler {
    public void handle(User user);
}
