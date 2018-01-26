package com.org.asean.javase.io;

import org.junit.Test;

import java.io.*;

public class TestObjectInputOutputStream {

    @Test
    public void testObjectOutputStream(){

        File file = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/Person.txt");
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            Person person = new Person(12,"Tom",22);
            oos.writeObject(person);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectInputStream(){
        File file = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/Person.txt");

        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            Person person =  (Person)ois.readObject();
            System.out.println(person.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPerson2Array(){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){

            Person person = new Person(1,"Tom",23);
            oos.writeObject(person);
            byte[] bytes = baos.toByteArray();
            System.out.println(bytes.length);
            for(byte b :bytes){
                System.out.print(b + "\t");
            }
         //   System.out.println(baos.toByteArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
