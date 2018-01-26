/*     */ package com.xman.common.rpc.registry.zk.client.impl;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.rpc.registry.zk.client.AbstractZookeeperClient;
/*     */ import com.xman.common.rpc.registry.zk.client.ChildListener;
/*     */ import java.util.List;
/*     */ import org.I0Itec.zkclient.IZkChildListener;
/*     */ import org.I0Itec.zkclient.IZkStateListener;
/*     */ import org.I0Itec.zkclient.ZkClient;
/*     */ import org.I0Itec.zkclient.exception.ZkNoNodeException;
/*     */ import org.I0Itec.zkclient.exception.ZkNodeExistsException;
/*     */ import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
/*     */ 
/*     */ 
/*     */ public class ZkclientZookeeperClient
/*     */   extends AbstractZookeeperClient<IZkChildListener>
/*     */ {
/*     */   private final ZkClient client;
/*  19 */   private volatile Watcher.Event.KeeperState state = Watcher.Event.KeeperState.SyncConnected;
/*     */   
/*     */   public ZkclientZookeeperClient(String hosts, int sessionTimeout, int connTimeout) {
/*  22 */     super(hosts);
/*  23 */     this.client = new ZkClient(hosts, sessionTimeout, connTimeout);
/*     */     
/*  25 */     this.client.subscribeStateChanges(new IZkStateListener() {
/*     */       public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
/*  27 */         ZkclientZookeeperClient.this.state = state;
/*  28 */         if (state == Watcher.Event.KeeperState.Disconnected) {
/*  29 */           ZkclientZookeeperClient.this.stateChanged(0);
/*  30 */         } else if (state == Watcher.Event.KeeperState.SyncConnected)
/*  31 */           ZkclientZookeeperClient.this.stateChanged(1);
/*     */       }
/*     */       
/*     */       public void handleNewSession() throws Exception {
/*  35 */         ZkclientZookeeperClient.this.stateChanged(2);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */   public Object readData(String path, boolean ifReturnNull)
/*     */   {
/*  43 */     return this.client.readData(path, ifReturnNull);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeData(String path, Object data)
/*     */   {
/*  49 */     this.client.writeData(path, data);
/*     */   }
/*     */   
/*     */   public void createPersistent(String path) {
/*     */     try {
/*  54 */       this.client.createPersistent(path, true);
/*     */     }
/*     */     catch (ZkNodeExistsException e) {}
/*     */   }
/*     */   
/*     */   public void createEphemeral(String path) {
/*     */     try {
/*  61 */       this.client.createEphemeral(path);
/*     */     } catch (ZkNodeExistsException e) {
/*  63 */       this.logger.warn("path [{}] already exist, delete it:" + path);
/*  64 */       delete(path);
/*  65 */       this.client.createEphemeral(path);
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(String path) {
/*     */     try {
/*  71 */       this.client.delete(path);
/*     */     }
/*     */     catch (ZkNoNodeException e) {}
/*     */   }
/*     */   
/*     */   public List<String> getChildren(String path) {
/*     */     try {
/*  78 */       return this.client.getChildren(path);
/*     */     } catch (ZkNoNodeException e) {}
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isConnected()
/*     */   {
/*  85 */     return this.state == Watcher.Event.KeeperState.SyncConnected;
/*     */   }
/*     */   
/*     */   public void doClose() {
/*  89 */     this.client.close();
/*     */   }
/*     */
/*     */   public IZkChildListener createTargetChildListener(String path, final ChildListener listener) {
/*  93 */     return new IZkChildListener()
/*     */     {
/*     */       public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
/*  96 */         listener.childChanged(parentPath, currentChilds);
/*     */       }
/*     */     };
/*     */   }
/*     */
/*     */   public List<String> addTargetChildListener(String path, IZkChildListener listener) {
/* 102 */     return this.client.subscribeChildChanges(path, listener);
/*     */   }
/*     */
/*     */   public void removeTargetChildListener(String path, IZkChildListener listener) {
/* 106 */     this.client.unsubscribeChildChanges(path, listener);
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/impl/ZkclientZookeeperClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */