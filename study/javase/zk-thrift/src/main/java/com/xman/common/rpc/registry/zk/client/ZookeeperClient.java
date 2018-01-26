package com.xman.common.rpc.registry.zk.client;

import java.util.List;

public abstract interface ZookeeperClient
{
  public static final String ZK_CURATOR_CLIENT = "curator";
  public static final String ZK_ZKCLIENT_CLIENT = "zkclient";
  
  public abstract void create(String paramString, boolean paramBoolean);
  
  public abstract void delete(String paramString);
  
  public abstract List<String> getChildren(String paramString);
  
  public abstract Object readData(String paramString, boolean paramBoolean);
  
  public abstract void writeData(String paramString, Object paramObject);
  
  public abstract List<String> addChildListener(String paramString, ChildListener paramChildListener);
  
  public abstract void removeChildListener(String paramString, ChildListener paramChildListener);
  
  public abstract void addStateListener(StateListener paramStateListener);
  
  public abstract void removeStateListener(StateListener paramStateListener);
  
  public abstract boolean isConnected();
  
  public abstract void close();
  
  public abstract String getHosts();
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/ZookeeperClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */