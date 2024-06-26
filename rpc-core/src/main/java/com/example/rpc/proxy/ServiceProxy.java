package com.example.rpc.proxy;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.example.rpc.RpcApplication;
import com.example.rpc.config.RpcConfig;
import com.example.rpc.model.RpcRequest;
import com.example.rpc.model.RpcResponse;
import com.example.rpc.serializer.JdkSerializer;
import com.example.rpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

/**
 * 服务代理类：由代理对象完成请求和响应
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = null;
        ServiceLoader<Serializer> serializerLoader = ServiceLoader.load(Serializer.class);
        for (Serializer item : serializerLoader) {
            serializer = item;
        }
        if (serializer == null) {
            serializer = new JdkSerializer();
        }

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .args(args)
                .build();

        byte[] bytes = serializer.serialize(rpcRequest);
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        String url = "http://" + rpcConfig.getHost() + ":" + rpcConfig.getPort();
        try (HttpResponse response = HttpRequest.post(url).body(bytes).execute()) {
            byte[] result = response.bodyBytes();
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        } catch (IOException e) {
            Console.error(e);
        }
        return null;
    }
}
