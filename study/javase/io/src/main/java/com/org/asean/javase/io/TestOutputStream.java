package com.org.asean.javase.io;

import org.junit.Test;

import java.io.*;

public class TestOutputStream {

    @Test
    public void testOutputStream(){

        File file = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/aaa.txt");

        try( FileOutputStream fos = new FileOutputStream(file)){
            fos.write(new String("I am a student").getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testCoye(){
        File inFile = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/aaa.txt");
        File outFile = new File("/Users/wsl/IdeaProjects/study/javase/io/src/main/resources/ccc.txt");
        try(FileInputStream fis = new FileInputStream(inFile);
            FileOutputStream fos = new FileOutputStream(outFile)){

            byte[] b = new byte[20];

            int len ;
            while((len = fis.read(b)) != -1){
                fos.write(b,0,len);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
