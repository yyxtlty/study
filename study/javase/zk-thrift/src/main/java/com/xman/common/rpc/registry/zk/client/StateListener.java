package com.xman.common.rpc.registry.zk.client;

public abstract interface StateListener
{
  public static final int DISCONNECTED = 0;
  public static final int CONNECTED = 1;
  public static final int RECONNECTED = 2;
  
  public abstract void stateChanged(int paramInt);
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/StateListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */