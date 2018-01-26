package com.org.asean.javase.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    @Test
    public void client() throws IOException {

        //1.创建客户端Socket,指定服务器地址
        Socket socket = new Socket("localhost",9898);
        //2.客户端向服务器发送信息
        try( OutputStream os = socket.getOutputStream();
             PrintWriter pw =new PrintWriter(os)){//将输出流包装成打印流
             pw.write("用户名：tom,密码：111" +"\n");
             pw.write("aaaaaaaaa \n");
             pw.flush();

             //获取输出流
           // socket.shutdownOutput();
        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
