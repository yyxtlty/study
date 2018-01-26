package com.xman.common.rpc.lb;

import com.xman.common.rpc.Service;
import java.util.List;

public abstract interface LoadBalance
{
  public static final String LOADBALANCE_RR = "roundrobin";
  public static final String LOADBALANCE_RAND = "random";
  public static final String LOADBALANCE_BROADCASE = "broadcast";
  
  public abstract Service select(List<Service> paramList);
}


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/lb/LoadBalance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */