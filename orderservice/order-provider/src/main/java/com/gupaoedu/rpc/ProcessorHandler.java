package com.gupaoedu.rpc;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 处理客户端请请去
 * 利用反射执行服务调用
 */
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());//获取来自客户端请求数据，服务端的输入流
            RpcRequest request = (RpcRequest) inputStream.readObject();//反序列化
            Object rs = invoke(request);
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

    public Object invoke(RpcRequest request) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //通过反射进行服务的调用
        Class clazz = Class.forName(request.getClassName());//com.gupaoedu.rpc.IOrderService
        //通过方法名和参数列表确定方法
        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
        return method.invoke(service, request.getArgs());

    }
}
