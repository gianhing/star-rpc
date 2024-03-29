package com.example.rpc.server;

import com.example.rpc.server.handler.VertxHttpServerHandler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;


public class VertxHttpServer implements IHttpServer {

    @Override
    public void start(int port) {
        // 创建 Vertx 实例
        Vertx vertx = Vertx.vertx();
        // 创建 HttpServer 服务器
        HttpServer httpServer = vertx.createHttpServer();

        // 设置请求处理器
        httpServer.requestHandler(new VertxHttpServerHandler());

        // 启动服务器并监听端口
        httpServer.listen(port, res -> {
            if (res.succeeded()) {
                System.out.println("Server is listening on port " + port);
            } else {
                System.err.println("Failed to start Server: " + res.cause());
            }
        });
    }
}
