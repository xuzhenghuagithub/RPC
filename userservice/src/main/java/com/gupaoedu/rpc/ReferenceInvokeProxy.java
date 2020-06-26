package com.gupaoedu.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {
    @Autowired
    private RemoteIonvocationHandler remoteIonvocationHandler;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyReference.class)) {
                //对加了MyRefenrence这个注解的字段，设置一个代理的值，如IOrderService字段，利用动态代理调用远程方法
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, remoteIonvocationHandler);
                try {
                    //不能代理private修饰的字段
                    field.set(bean, proxy);//相当于针对加了MyReference注解的类的字段进行了动态代理创建，可以实现remoteIonvocationHandler
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;

    }

}
