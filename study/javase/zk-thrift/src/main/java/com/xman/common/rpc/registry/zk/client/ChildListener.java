package com.xman.common.rpc.registry.zk.client;

import java.util.List;

public abstract interface ChildListener
{
  public abstract void childChanged(String paramString, List<String> paramList);
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/ChildListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */