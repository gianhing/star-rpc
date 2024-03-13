package com.example.provider.service.impl;

import com.example.common.model.User;
import com.example.common.service.UserService;


public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getUsername());
        return user;
    }
}
