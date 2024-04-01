package com.example.rpc.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtil {

    /**
     * 读取配置文件并转成目标对象
     * @param clazz 目标类
     * @param prefix 前缀
     * @return 目标对象
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix) {
        return loadConfig(clazz, prefix, "");
    }

    /**
     * 读取配置文件（分环境）并转成目标对象
     * @param clazz 目标类
     * @param prefix 前缀
     * @param env 环境
     * @return 目标对象
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix, String env) {
        StringBuilder configFile = new StringBuilder("application");
        if (StrUtil.isNotBlank(env)) {
            configFile.append("-").append(env);
        }
        configFile.append(".properties");
        Props props = new Props(configFile.toString());
        System.out.println(props);
        return props.toBean(clazz, prefix);
    }
}
