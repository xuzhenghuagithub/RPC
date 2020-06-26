package com.gupaoedu.rpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 远程调用处理器
 * 创建远程连接，调用服务
 */
@Component
public class RemoteIonvocationHandler implements InvocationHandler {
    @Value("${rpc.host}")
    private String host;
    @Value("${rpc.port}")//编码若不为UTF-8则注入int类型失败
    private int port;

    /*
    //new
    public RemoteIonvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }*/

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        //建立远程连接
        RpcNetTransport rpcNetTransport = new RpcNetTransport(this.host, this.port);
        //传递数据
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setArgs(args);//方法参数
        rpcRequest.setClassName(method.getDeclaringClass().getName());//方法的接口路径名,对象引用目的地com.gupaoedu.rpc.IOrderService
        rpcRequest.setTypes(method.getParameterTypes());//调用方法参数列表
        rpcRequest.setMethodName(method.getName());//调用方法名
        //发送请求
        return rpcNetTransport.send(rpcRequest);
    }

}
