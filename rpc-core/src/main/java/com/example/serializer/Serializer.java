package com.example.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 */
public interface Serializer {

    /**
     * 序列化
     * @param object 对象
     * @return 字节数组
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * @param bytes 字节数组
     * @param clazz 转换对象类
     * @return 转换对象
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException;
}
