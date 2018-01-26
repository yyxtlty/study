package com.org.asean.javase.curator;

public class Test2 extends  Test {


    @Override
    protected void abstractRegist() {
        System.out.println("test2");
    }

    public static void main(String[] args) {
        Test t = new Test1();
        t.regist();
    }
}
