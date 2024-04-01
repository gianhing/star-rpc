package com.example.provider;

import com.example.common.service.UserService;
import com.example.provider.service.impl.UserServiceImpl;
import com.example.rpc.RpcApplication;
import com.example.rpc.config.RpcConfig;
import com.example.rpc.registry.LocalRegistry;
import com.example.rpc.server.IHttpServer;
import com.example.rpc.server.VertxHttpServer;

public class ProviderApplication {
    public static void main(String[] args) {

        RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务
        IHttpServer httpServer = new VertxHttpServer();
        httpServer.start(rpcConfig.getPort());
    }
}
