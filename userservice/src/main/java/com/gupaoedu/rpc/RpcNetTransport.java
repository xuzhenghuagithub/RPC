package com.gupaoedu.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * rpc网络传输
 */
public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() throws IOException {
        return new Socket(this.host, this.port);
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = newSocket();
            outputStream = new ObjectOutputStream(socket.getOutputStream());//获取客户端的输出六
            outputStream.writeObject(rpcRequest);//传递数据到服务端
            outputStream.flush();

            //获取服务端的返回数据
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "发送成功";
    }
}
