package com.org.lty.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateModable;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class CuratorCli {

    private CuratorFramework client = null;

    public CuratorCli(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("base").build();
        client.start();
    }

    public void closeClient(){
        if(null!=client){
            this.client.close();
        }
    }

    public void createNode(String path,String data) throws Exception {
        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path,data.getBytes());
    }

    public static void main(String[] args)  {

        CuratorCli client = new CuratorCli();
        try {

            client.createNode("/curator/node1","123");
            System.out.println("创建node成功！");
            Thread.sleep(30000);
            System.out.println("创建node销毁");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.closeClient();
        }

    }



}
