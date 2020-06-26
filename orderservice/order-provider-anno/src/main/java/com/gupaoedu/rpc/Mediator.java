package com.gupaoedu.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 中介类
 * 储存要发布的类和方法信息
 */
public class Mediator {
    //储存要发布的服务的实例
    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();

    /**
     * 传入客户端的调用信息，利用反射执行服务
     * @param request
     * @return
     */
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
