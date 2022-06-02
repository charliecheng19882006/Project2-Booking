package com.laioffer.booking.controller;

import org.springframework.web.bind.annotation.RestController;
import com.laioffer.booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.laioffer.booking.model.User;
import com.laioffer.booking.model.UserRole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    @PostMapping("/register/guest")
    public void addGuest(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/register/host")
    public void addHost(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_HOST);
    }

}

