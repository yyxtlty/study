package com.xman.common.rpc.pool;

import com.xman.common.rpc.Server;

public abstract class ConnFactory<T>
{
  public abstract T makeObject(Server paramServer)
    throws Exception;
  
  public abstract void destroyObject(T paramT)
    throws Exception;
  
  public abstract boolean validateObject(T paramT);
  
  public abstract void activateObject(T paramT)
    throws Exception;
  
  public abstract void passivateObject(T paramT)
    throws Exception;
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/pool/ConnFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */