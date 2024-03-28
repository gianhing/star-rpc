package com.example.registry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册器
 */
public class LocalRegistry {

    private static final ConcurrentHashMap<String, Class<?>> LOCAL_REGISTRY = new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param serviceName 服务名称
     * @param clazz 服务实现类
     */
    public static void register(String serviceName, Class<?> clazz) {
        LOCAL_REGISTRY.put(serviceName, clazz);
    }

    /**
     * 获取服务
     * @param serviceName 服务名称
     * @return 服务实现类
     */
    public static Class<?> get(String serviceName) {
        return LOCAL_REGISTRY.get(serviceName);
    }

    /**
     * 移除服务
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        LOCAL_REGISTRY.remove(serviceName);
    }
}
