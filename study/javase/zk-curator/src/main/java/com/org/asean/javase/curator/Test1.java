package com.org.asean.javase.curator;

public class Test1 extends Test {
    @Override
    protected void abstractRegist() {
        System.out.println("11111111");
    }


    public static void main(String[] args) {
        Test t = new Test1();
        t.regist();
    }
}
