package com.asean.gril;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapUtil {

    public static void main(String[] args) {

        Map m = new HashMap(10*1000);
        MyRunnable runnable = new MyRunnable(m);
      //  Thread thread = new Thread(runnable);

        for(int i=0;i<10;i++){
            new MyRunnable(m).start();
            // new MyRunnable(m).run();


        }


        System.out.println(m.size()+"rrrrr");
    }

}

class MyRunnable extends   Thread{

     Map map;

    public MyRunnable(Map map){
        this.map = map;
    }

    @Override
    public void run() {

        for(int i=0;i<1000;i++){
            map.put(this.getName() + i,i);
        }

        //如果是线程安全,那么HashMap的大小,最后能够达到1W.
        System.out.println(this.getName()+": "+map.size());



    }
}
