package com.example.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;


public class VertxHttpServer implements IHttpServer {

    @Override
    public void start(int port) {
        // 创建 Vertx 实例
        Vertx vertx = Vertx.vertx();
        // 创建 HttpServer 服务器
        HttpServer httpServer = vertx.createHttpServer();

        // 设置请求处理器
        httpServer.requestHandler(request -> {
            System.out.println("Received url: [" + request.method() + "] " + request.uri());

            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x Http Server");
        });

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
