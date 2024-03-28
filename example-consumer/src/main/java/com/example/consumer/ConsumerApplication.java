package com.example.consumer;

import com.example.common.model.User;
import com.example.common.service.UserService;
import com.example.proxy.ServiceProxyFactory;

public class ConsumerApplication {
    public static void main(String[] args) {

        User user = new User();
        user.setUsername("test");

        // 获取代理对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User newUser = userService.getUser(user);

        if (newUser != null) {
            System.out.println(newUser.getUsername());
        } else {
            System.out.println("user == null");
        }
    }
}
