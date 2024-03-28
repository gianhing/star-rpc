package com.example.provider;

import com.example.common.service.UserService;
import com.example.provider.service.impl.UserServiceImpl;
import com.example.registry.LocalRegistry;
import com.example.server.IHttpServer;
import com.example.server.VertxHttpServer;

public class ProviderApplication {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务
        IHttpServer httpServer = new VertxHttpServer();
        httpServer.start(8080);
    }
}
