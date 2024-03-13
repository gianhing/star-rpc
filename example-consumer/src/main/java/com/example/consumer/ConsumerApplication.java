package com.example.consumer;

import com.example.common.model.User;
import com.example.common.service.UserService;

public class ConsumerApplication {
    public static void main(String[] args) {

        User user = new User();
        user.setUsername("test");

        UserService userService = null;
        User newUser = userService.getUser(user);

        if (newUser != null) {
            System.out.println(newUser.getUsername());
        } else {
            System.out.println("user == null");
        }
    }
}
