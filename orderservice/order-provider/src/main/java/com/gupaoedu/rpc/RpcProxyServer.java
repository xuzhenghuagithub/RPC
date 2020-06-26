package com.gupaoedu.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * roc远程程序调用代理服务
 * 发布服务，接受客户端请求，调用处理器进行服务
 */
public class RpcProxyServer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publishServer(Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();//监听客户端请求
                executorService.execute(new ProcessorHandler(accept,service));//socket堵塞io，开启线程能够使后续请求不受受影响
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ;

}
