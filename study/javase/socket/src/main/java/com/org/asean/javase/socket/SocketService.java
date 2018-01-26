package com.org.asean.javase.socket;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

    @Test
    public void testServerSocket() {
        try(ServerSocket serverSocket = new ServerSocket(9999);){
            //1.服务器端创建一个ServerSocket，指定端口，并监听此端口

            //2.调用accept()方法开始监听，等待客户端的连接
            System.out.println("****服务器即将启动，等待客户端的连接***");
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);

                serverThread.start();
            }




          /*  try(InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);){

                //3.通过输入输出流实现，读取客户端信息


                String info = null;
                while((info=br.readLine())!=null){//
                    System.out.println("我是服务器，客户端说"+info);
                }

            }catch (IOException e) {
                e.printStackTrace();
            }*/
           // socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
