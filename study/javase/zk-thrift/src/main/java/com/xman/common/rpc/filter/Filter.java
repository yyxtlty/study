package com.xman.common.rpc.filter;

import com.xman.common.rpc.invoke.Invocation;
import com.xman.common.rpc.invoke.Invoker;

public abstract interface Filter
{
  public abstract Object invoke(Invoker paramInvoker, Invocation paramInvocation)
    throws Throwable;
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/Filter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */