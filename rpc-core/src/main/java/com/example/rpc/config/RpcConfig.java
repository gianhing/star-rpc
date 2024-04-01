package com.example.rpc.config;

import lombok.Data;

/**
 * RPC 配置
 */
@Data
public class RpcConfig {

    /**
     * 应用名称
     */
    private String name = "star-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务主机地址
     */
    private String host = "localhost";

    /**
     * 服务端口号
     */
    private Integer port = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;
}
