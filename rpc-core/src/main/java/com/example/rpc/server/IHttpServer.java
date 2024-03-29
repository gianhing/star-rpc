package com.example.rpc.server;

/**
 * HTTP 服务器接口
 */
public interface IHttpServer {

    /**
     * 启动服务
     * @param port 端口号
     */
    void start(int port);
}
