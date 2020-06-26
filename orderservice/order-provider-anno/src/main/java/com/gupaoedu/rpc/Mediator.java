package com.gupaoedu.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 中介类
 * 储存钥发布的类和方法信息
 */
public class Mediator {
    //储存钥发布的服务的实例
    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();

    public static Object processor(RpcRequest request) {
        String key = request.getClassName() + "." + request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (beanMethod == null) {
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean, request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
