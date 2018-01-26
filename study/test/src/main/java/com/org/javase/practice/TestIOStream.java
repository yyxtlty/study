package com.org.javase.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestIOStream {


    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("");

        InputStream fileInputStream = new FileInputStream(file);

    }
}
