package com.org.asean.javase.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.rmi.registry.Registry;

public class TestCurator {

    CuratorFramework client = null;

    public TestCurator() {

        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181").sessionTimeoutMs(1000)
                .retryPolicy(policy).namespace("base").build();
        client.start();
    }

    public void clientClose(){
        if(client!=null){
            client.close();
        }
    }

    public void createNode(String path,byte[] data) throws Exception {

        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path, data);
    }

    public void readNode(String path) throws Exception {
        
        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath(path);

        System.out.println("读取节点：" + path + "的数据：" + new String(data));
        System.out.println(stat.toString());

    }

    public byte[] getNode(String path) throws Exception {

        Stat stat = new Stat();
       return client.getData().storingStatIn(stat).forPath(path);

        //System.out.println("读取节点：" + path + "的数据：" + new String(data));
        //System.out.println(stat.toString());

    }

    public static void main(String[] args) {

        TestCurator ct = null;

        try {
            ct = new TestCurator();
            System.out.println("连接成功");
            ct.createNode("/curator/test/node1","test-node1".getBytes());
            System.out.println("创建成功");
         //   ct.readNode("/curator/test/node1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ct.clientClose();
        }
    }

}
