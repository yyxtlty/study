package com.xman.common.rpc.registry;

import com.xman.common.rpc.Service;

public abstract interface Registry
{
  public abstract String getHosts();
  
  public abstract boolean isAvailable();
  
  public abstract void destroy();
  
  public abstract void register(Service paramService);
  
  public abstract void unregister(Service paramService);
  
  public abstract void subscribe(String paramString, NotifyListener paramNotifyListener);
  
  public abstract void unsubscribe(String paramString, NotifyListener paramNotifyListener);
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/Registry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */