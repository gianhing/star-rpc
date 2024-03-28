package com.example.proxy;

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
        Object object = Proxy.newProxyInstance(serviceClazz.getClassLoader(),
                new Class[]{serviceClazz},
                new ServiceProxy());
        return serviceClazz.cast(object);
    }
}
