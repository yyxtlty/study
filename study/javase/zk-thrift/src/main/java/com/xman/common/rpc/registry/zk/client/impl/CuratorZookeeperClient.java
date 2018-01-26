/*     */ package com.xman.common.rpc.registry.zk.client.impl;
/*     */ 
/*     */ import com.xman.common.rpc.registry.zk.client.AbstractZookeeperClient;
/*     */ import com.xman.common.rpc.registry.zk.client.ChildListener;
/*     */ import java.util.List;
/*     */ import org.apache.curator.framework.CuratorFramework;
/*     */ import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
/*     */ import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
/*     */ import org.apache.curator.framework.api.BackgroundPathable;
/*     */ import org.apache.curator.framework.api.CreateBuilder;
/*     */ import org.apache.curator.framework.api.CuratorWatcher;
/*     */ import org.apache.curator.framework.api.DeleteBuilder;
/*     */ import org.apache.curator.framework.api.GetChildrenBuilder;
/*     */ import org.apache.curator.framework.state.ConnectionState;
/*     */ import org.apache.curator.retry.RetryNTimes;
/*     */ import org.apache.zookeeper.CreateMode;
/*     */ import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NoNodeException;
/*     */ import org.apache.zookeeper.KeeperException.NodeExistsException;
/*     */ import org.apache.zookeeper.WatchedEvent;
/*     */ 
/*     */ public class CuratorZookeeperClient extends AbstractZookeeperClient<CuratorWatcher>
/*     */ {
/*     */   private final CuratorFramework client;
/*     */   
/*     */   public CuratorFramework getClient()
/*     */   {
/*  27 */     return this.client;
/*     */   }
/*     */   
/*     */   public CuratorZookeeperClient(String hosts, int sessionTimeout, int connTimeout) {
/*  31 */     super(hosts);
/*  32 */     CuratorFrameworkFactory.Builder builder = org.apache.curator.framework.CuratorFrameworkFactory.builder().connectString(hosts).retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(connTimeout).sessionTimeoutMs(sessionTimeout);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */     this.client = builder.build();
/*  39 */     this.client.getConnectionStateListenable().addListener(new org.apache.curator.framework.state.ConnectionStateListener() {
/*     */       public void stateChanged(CuratorFramework client, ConnectionState state) {
/*  41 */         if (state == ConnectionState.LOST) {
/*  42 */           CuratorZookeeperClient.this.stateChanged(0);
/*  43 */         } else if (state == ConnectionState.CONNECTED) {
/*  44 */           CuratorZookeeperClient.this.stateChanged(1);
/*  45 */         } else if (state == ConnectionState.RECONNECTED) {
/*  46 */           CuratorZookeeperClient.this.stateChanged(2);
/*     */         }
/*     */       }
/*  49 */     });
/*  50 */     this.client.start();
/*     */   }
/*     */   
/*     */   public void createPersistent(String path) {
/*     */     try {
/*  55 */       this.client.create().forPath(path);
/*     */     }
/*     */     catch (KeeperException.NodeExistsException e) {}catch (Exception e) {
/*  58 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void createEphemeral(String path) {
/*     */     try {
/*  64 */       ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.EPHEMERAL)).forPath(path);
/*     */     } catch (KeeperException.NodeExistsException e) {
/*  66 */       this.logger.warn("path [{}] already exist, delete it:" + path);
/*  67 */       delete(path);
/*     */       try {
/*  69 */         ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.EPHEMERAL)).forPath(path);
/*     */       } catch (Exception e1) {
/*  71 */         throw new IllegalStateException(e1.getMessage(), e1);
/*     */       }
/*     */     } catch (Exception e) {
/*  74 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(String path) {
/*     */     try {
/*  80 */       this.client.delete().forPath(path);
/*     */     }
/*     */     catch (KeeperException.NoNodeException e) {}catch (Exception e) {
/*  83 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<String> getChildren(String path) {
/*     */     try {
/*  89 */       return (List)this.client.getChildren().forPath(path);
/*     */     } catch (KeeperException.NoNodeException e) {
/*  91 */       return null;
/*     */     } catch (Exception e) {
/*  93 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/*  98 */     return this.client.getZookeeperClient().isConnected();
/*     */   }
/*     */   
/*     */   public void doClose() {
/* 102 */     this.client.close();
/*     */   }
/*     */   
/*     */   private class CuratorWatcherImpl implements CuratorWatcher
/*     */   {
/*     */     private volatile ChildListener listener;
/*     */     
/*     */     public CuratorWatcherImpl(ChildListener listener) {
/* 110 */       this.listener = listener;
/*     */     }
/*     */     
/*     */     public void unwatch() {
/* 114 */       this.listener = null;
/*     */     }
/*     */     
/*     */     public void process(WatchedEvent event) throws Exception {
/* 118 */       if ((this.listener != null) && (event.getPath() != null)) {
/* 119 */         this.listener.childChanged(event.getPath(), (List)((BackgroundPathable)CuratorZookeeperClient.this.client.getChildren().usingWatcher(this)).forPath(event.getPath()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected CuratorWatcher createTargetChildListener(String path, ChildListener listener)
/*     */   {
/* 127 */     return new CuratorWatcherImpl(listener);
/*     */   }
/*     */   
/*     */   protected List<String> addTargetChildListener(String path, CuratorWatcher listener)
/*     */   {
/*     */     try {
/* 133 */       return (List)((BackgroundPathable)this.client.getChildren().usingWatcher(listener)).forPath(path);
/*     */     } catch (KeeperException.NoNodeException e) {
/* 135 */       return null;
/*     */     } catch (Exception e) {
/* 137 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void removeTargetChildListener(String path, CuratorWatcher listener)
/*     */   {
/* 143 */     ((CuratorWatcherImpl)listener).unwatch();
/*     */   }
/*     */   
/*     */   public Object readData(String path, boolean ifReturnNull)
/*     */   {
/*     */     try
/*     */     {
/* 150 */       return this.client.getData().forPath(path);
/*     */     } catch (Exception e) {
/* 152 */       throw new IllegalStateException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeData(String path, Object data)
/*     */   {
/* 159 */     throw new IllegalStateException("unimplement method");
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/impl/CuratorZookeeperClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */