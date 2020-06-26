package com.gupaoedu.rpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OrderServiceimpl orderServiceimpl = new OrderServiceimpl();
        RpcProxyServer server = new RpcProxyServer();
        server.publishServer(orderServiceimpl,8080);
        System.out.println( "服务端启动" );
    }
}
