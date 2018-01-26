package com.org.asean.javase.socket.test;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceSocket {

    @Test
    public void ServiceSocket(){

        try( ServerSocket serverSocket = new ServerSocket(9999)){
            while (true){
                try(Socket  socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

                    Person person = (Person) objectInputStream.readObject();
                    if(person!=null){
                        System.out.println(person.toString());
                    }
                   //String str = "";
/*
                    while((str=bufferedReader.readLine()) != null){
                        System.out.println(str);
                    }
                    System.out.println("over");*/
                    //String str = bufferedReader.readLine();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
