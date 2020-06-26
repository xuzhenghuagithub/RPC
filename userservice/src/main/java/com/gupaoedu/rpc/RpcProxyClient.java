package com.gupaoedu.rpc;


/**
 * 动态代理执行远程调用
 * 解耦，传入不同的接口参数和远程成服务器信息，调用不同的远程接口
 */
public class RpcProxyClient {
    /*public <T> T clientProxy(final Class<T> interfacesCls, final String host, final int port) {
        //动态代理
        return (T) Proxy.newProxyInstance(interfacesCls.getClassLoader(), new Class<?>[]{interfacesCls}
        , new RemoteIonvocationHandler(host,port));


    }*/

}
