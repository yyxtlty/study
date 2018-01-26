package om.asean.rmi.zk.test;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;

import java.io.IOException;

public class BitWatcher implements Watcher {

    BizZookeeper bizZookeeper = new BizZookeeper();
//    BizZookeeper  zookeeper = new BizZookeeper();
ZooKeeper zooKeeper;
    void register (String serverName) throws IOException, KeeperException, InterruptedException {
         zooKeeper = bizZookeeper.getZooKeeper(this);
        zooKeeper.create("/bit",serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("创建成功！！！");

    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }


    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        BitWatcher bitWatcher = new BitWatcher();

        Stat stat = new Stat();
        int i = 0 ;
        stat.setIp(args[i++]);
        stat.setPort(args[i++]);
        stat.setName(args[i++]);
        stat.setNum(0);
        stat.setStatus("wait");
        bitWatcher.register(JSON.toJSONString(stat));
        Thread.sleep(Long.MAX_VALUE);


    }
}
