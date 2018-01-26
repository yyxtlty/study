package com.org.asean.javase.socket.test;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {


    @Test
    public void client(){

        Person person = new Person(2,"Nginx",12);

        try( Socket socket = new Socket("localhost",9898);
             OutputStream outputStream = socket.getOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
           // OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            objectOutputStream.writeObject(person);
           // objectOutputStream.flush();
           /* outputStreamWriter.write("hello world中国人 \n" );
            outputStreamWriter.flush();
            outputStreamWriter.write("jijoijoij");
            outputStreamWriter.flush();*/

            //outputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


