package om.asean.rmi.zk.test;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * zookeeper 注册中心
 */
public class BizZookeeper {

    private ZooKeeper zooKeeper;
    public static final String host ="localhost:2181";

    public ZooKeeper getZooKeeper(Watcher watcher) throws IOException {
        zooKeeper = new ZooKeeper(host,3000,watcher);
        return zooKeeper;
    }
}
