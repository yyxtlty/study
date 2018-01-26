package com.org.asean.javase.io;

import org.junit.Test;

import java.io.*;

public class TestInputStream {

    @Test
    public void testInputStream() throws IOException {

        File file = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/aaa.txt");

        FileInputStream fis = new FileInputStream(file);

        byte[] b = new byte[5];

        int len = fis.read(b);



    }

    @Test
    public void testByte() throws IOException, ClassNotFoundException {
        String a = "abcdefg";
        Person person = new Person(1,"Tom",23);
     //   byte[] bytes = a.getBytes();
        // byte[] byte = toBytes(person);
        byte[] bytes = person.toString().getBytes();
        byte[] b = toBytes(person);
        System.out.println(bytes.length);
        System.out.println(b.length);

        for(int i=0;i<bytes.length;i++){
            System.out.print(bytes[i] +"\t");

        }
        System.out.println("");
        for(byte j : b){
            System.out.print(j +"\t");
        }
        Person person1 = (Person) toObj(bytes);
        System.out.println(person1.toString());

    }

    public  byte[] toBytes(Serializable obj) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ){
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public  Serializable toObj(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        )
        {
            Object o = objectInputStream.readObject();
            return (Serializable) o;
        }
    }

    @Test
    public void test(){
        //System.out.println(testTryCatch());
    }



    @Test
    public void  testTryCatch(){
        File file = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/aaa.txt");
//        try (InputStream inputStream = new FileInputStream(file)){
        String a = "abc";

        try{

            a = "bcd";
            System.out.println(a);
           // return a;
        }catch(Exception e){

            e.printStackTrace();
           // return "bcd";
        }finally {
            a = "hij";
            System.out.println(a);
            System.out.println("finally");
           // return a;
        }


    }


    @Test
    public void testByte1(){
        String a = "abcdefg";
        a.getBytes();
        System.out.println(a.getBytes());
    }

}

