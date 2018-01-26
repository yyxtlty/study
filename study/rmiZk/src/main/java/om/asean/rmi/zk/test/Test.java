package om.asean.rmi.zk.test;

import org.apache.zookeeper.*;

import java.io.IOException;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Test {


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper z = new ZooKeeper("localhost:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        System.out.println("121212"+ z.toString());
        z.create("/registry/", "wer".getBytes(), OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("“创建成功！");
        Thread.sleep(Long.MAX_VALUE);

    }

    /*public static Watcher getWatcher(){
        return  new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        };
    }*/

}
