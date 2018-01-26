package com.org.asean.javase.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

    //和本线程相关的socket
    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
      //  Socket socket = serverSocket.accept();

        try(InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);){

            //3.通过输入输出流实现，读取客户端信息


            String info = null;
            while((info=br.readLine())!=null){//
                System.out.println("我是服务器，客户端说"+info);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
