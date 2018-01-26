package com.org.javase.practice.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInputStream {


    @Test
    public void testInputStream() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    new File("/Users/wsl/IdeaProjects/study/test/src/main/resources/aaa.txt"));

            int i = fis.read();
            while (i !=-1){
                System.out.println((char)i);
                i = fis.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testInputStream1() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    new File("/Users/wsl/IdeaProjects/study/test/src/main/resources/aaa.txt"));

            byte[] b = new byte[5];
            int len = fis.read(b);
            while (len !=-1){
                /*for(int i=0;i<len;i++){
                    System.out.println((char)b[i]);

                }*/
                String str = new String(b,0,len);
                System.out.println(str);
                len = fis.read(b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
