package com.org.asean.javase.mina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        client.start();
    }

    public void start(){

        try( Socket socket = new Socket("localhost",9898);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));){

            String inputContext;
            while(!(inputContext= inputReader.readLine()).equals("bye")){
                writer.write(inputContext +"\n");
                writer.flush();
            }
            System.out.println("aaaaa");

        }catch (Exception e){

        }
    }
}
