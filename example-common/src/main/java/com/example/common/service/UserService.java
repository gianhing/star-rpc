package com.example.common.service;

import com.example.common.model.User;

public interface UserService {

    /**
     * 获取用户
     * @param user 用户信息
     * @return 用户
     */
    User getUser(User user);
}
