package com.xman.common.rpc.invoke;

import java.util.Map;

public abstract interface Invocation
{
  public abstract Object[] getArguments();
  
  public abstract Object getImpl();
  
  public abstract Class<?> getIface();
  
  public abstract String getMethodName();
  
  public abstract Map<String, Object> getAttachments();
  
  public abstract Object getAttachment(String paramString);
  
  public abstract boolean isClient();
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/invoke/Invocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */