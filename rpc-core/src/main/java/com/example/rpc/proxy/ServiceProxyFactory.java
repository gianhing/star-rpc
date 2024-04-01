package com.example.rpc.proxy;

import com.example.rpc.RpcApplication;
import com.example.rpc.config.RpcConfig;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * @param serviceClazz 服务类
     * @return 服务代理对象
     */
    public static <T> T getProxy(Class<T> serviceClazz) {
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 判断是否模拟调用
        if (rpcConfig.isMock()) {
            return getMockProxy(serviceClazz);
        }

        Object object = Proxy.newProxyInstance(serviceClazz.getClassLoader(),
                new Class[]{serviceClazz},
                new ServiceProxy());
        return serviceClazz.cast(object);
    }

    /**
     * 根据服务类获取模拟代理对象
     * @param serviceClazz 服务类
     * @return 模拟代理对象
     */
    private static <T> T getMockProxy(Class<T> serviceClazz) {
        Object object = Proxy.newProxyInstance(serviceClazz.getClassLoader(),
                new Class[]{serviceClazz},
                new MockServiceProxy());
        return serviceClazz.cast(object);
    }
}
