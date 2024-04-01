package com.example.rpc;

import com.example.rpc.config.RpcConfig;
import com.example.rpc.util.ConfigUtil;

public class RpcApplication {

    private static volatile RpcConfig config;

    private static final String DEFAULT_CONFIG_PREFIX = "rpc";

    private RpcApplication() {

    }

    /**
     * RPC 配置初始化
     */
    public static void init() {
        RpcConfig defaultConfig;
        try {
            defaultConfig = ConfigUtil.loadConfig(RpcConfig.class, DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            defaultConfig = new RpcConfig();
        }
        init(defaultConfig);
    }

    /**
     * RPC 配置初始化
     * @param customConfig 自定义配置
     */
    public static void init(RpcConfig customConfig) {
        System.out.println("RpcConfig init: " + customConfig);
        config = customConfig;
    }

    /**
     * 获取 RPC 配置
     * @return RPC 配置
     */
    public static RpcConfig getRpcConfig() {
        if (config == null) {
            synchronized (RpcApplication.class) {
                if (config == null) {
                    init();
                }
            }
        }
        return config;
    }
}
