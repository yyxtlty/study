package com.xman.common.rpc.registry;

import com.xman.common.rpc.Service;
import java.util.List;

public abstract interface NotifyListener
{
  public abstract void notify(List<Service> paramList);
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/NotifyListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */