package com.gupaoedu.rpc;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class InitialMediator implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //bean创建之后加入到Mediator中
        if(bean.getClass().isAnnotationPresent(GPRemoteService.class)){
            //加了GPRemoteService注解的类才放入Mediator
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method:methods){
                String key = bean.getClass().getInterfaces()[0].getName()+"."+method.getName();//方法的类的第一个接口的路径名com.gupaoedu.rpc.IOrderService
                //String key = method.getDeclaringClass().getName()+"."+method.getName();//方法实例类的路径名com.gupaoedu.rpc.OrderServiceimpl.queryOrderList
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);

            }
        }
        return bean;
    }
}
