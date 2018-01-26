package om.asean.rmi.zk.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherExmple implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
//        ZooKeeper  zk = new ZooKeeper("localhost:2181",3000,);
    }
}
