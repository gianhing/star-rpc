package com.example.server.handler;

import cn.hutool.core.lang.Console;
import com.example.model.RpcRequest;
import com.example.model.RpcResponse;
import com.example.registry.LocalRegistry;
import com.example.serializer.JdkSerializer;
import com.example.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Http 请求处理（Vert.x）
 */
public class VertxHttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();
        // 记录日志
        System.out.println("Received url: [" + request.method() + "] " + request.uri());

        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                // 反序列化解析请求数据
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest == null) {
                // 如果请求为null，直接返回
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            Class<?> clazz = LocalRegistry.get(rpcRequest.getServiceName());
            try {
                // 利用反射调用服务方法
                Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
                Object result = method.invoke(clazz.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setType(method.getReturnType());
                rpcResponse.setMessage("success");
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                Console.error(e);
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 执行响应（将响应数据序列化后进行传输返回）
     * @param request 请求体
     * @param rpcResponse 响应数据
     * @param serializer 序列化器
     */
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse response = request.response();

        response.putHeader("content-type", "application/json");
        try {
            byte[] bytes = serializer.serialize(rpcResponse);
            response.end(Buffer.buffer(bytes));
        } catch (IOException e) {
            Console.error(e);
            response.end(Buffer.buffer());
        }
    }
}
