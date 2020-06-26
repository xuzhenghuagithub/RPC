package com.gupaoedu.rpc;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 处理客户端请请去
 * 利用反射执行服务调用
 */
public class ProcessorHandler implements Runnable {
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());//获取来自客户端请求数据，服务端的输入流
            RpcRequest request = (RpcRequest) inputStream.readObject();//反序列化

            Object rs = Mediator.processor(request);

            System.out.println("服务端执行结果："+rs.toString());

            //向客户端返回执行结果
            outputStream = new ObjectOutputStream(socket.getOutputStream());//服务端输出流，想客户端返回执行信息
            outputStream.writeObject(rs);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
