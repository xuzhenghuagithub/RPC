package com.gupaoedu.rpc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \
 * spring容器启动完成之后，会发布一个ContextRefreshedEvent事件
 * 在这里启动socketserver服务
 */
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();//监听客户端请求
                executorService.execute(new ProcessorHandler(accept));//socket堵塞io，开启线程能够使后续请求不受受影响
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
