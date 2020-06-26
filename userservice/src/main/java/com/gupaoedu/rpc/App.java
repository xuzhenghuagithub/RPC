package com.gupaoedu.rpc;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        /*IOrderService iOrderService = rpcProxyClient.clientProxy(IOrderService.class,"localhost", 8080);

        System.out.println("服务端返回结果"+iOrderService.queryOrderList());
        System.out.println("服务端返回结果"+iOrderService.queryOrderById());
        ITestService iTestService = rpcProxyClient.clientProxy(ITestService.class,"localhost", 8080);
        System.out.println("服务端返回结果"+iTestService.test());*/

        System.out.println("Hello World!");
    }
}
