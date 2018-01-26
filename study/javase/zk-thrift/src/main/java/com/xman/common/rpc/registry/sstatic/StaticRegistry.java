/*     */ package com.xman.common.rpc.registry.sstatic;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.Server;
/*     */ import com.xman.common.rpc.Service;
/*     */ import com.xman.common.rpc.registry.NotifyListener;
/*     */ import com.xman.common.rpc.registry.support.AbstractRegistry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.thrift.transport.TSocket;
/*     */ 
/*     */ 
/*     */ public class StaticRegistry
/*     */   extends AbstractRegistry
/*     */ {
/*  21 */   private static final ILog logger = LogFactory.getLog(StaticRegistry.class);
/*     */   
/*     */ 
/*     */ 
/*     */   private ScheduledFuture<?> checkTimer;
/*     */   
/*     */ 
/*     */   private List<Server> servers;
/*     */   
/*     */ 
/*  31 */   private int heartbeatPeriod = 5000;
/*     */   
/*     */   public StaticRegistry() {}
/*     */   
/*     */   public StaticRegistry(String hosts, int heartbeatPeriod)
/*     */   {
/*  37 */     this.hosts = hosts;
/*  38 */     this.heartbeatPeriod = heartbeatPeriod;
/*  39 */     init();
/*     */   }
/*     */   
/*     */   public void init()
/*     */   {
/*  44 */     String addrs = getHosts();
/*  45 */     if ((addrs == null) || ("".equals(addrs.trim()))) {
/*  46 */       throw new IllegalStateException("static service address == null");
/*     */     }
/*     */     
/*  49 */     parseServer(addrs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void parseServer(String addr)
/*     */   {
/*  66 */     List<Server> srvList = new ArrayList();
/*  67 */     String[] srv = StringUtils.split(addr, ',');
/*  68 */     for (int i = 0; i < srv.length; i++) {
/*  69 */       String server = srv[i];
/*  70 */       String[] info = StringUtils.split(server, ':');
/*  71 */       srvList.add(new Server(info[0], Integer.parseInt(info[1])));
/*     */     }
/*     */     
/*  74 */     Random rand = new Random();
/*  75 */     rand.setSeed(System.currentTimeMillis());
/*  76 */     Collections.shuffle(srvList, rand);
/*  77 */     this.servers = new ArrayList(srvList);
/*     */   }
/*     */   
/*     */   public void subscribe(String service, NotifyListener listener)
/*     */   {
/*  82 */     super.subscribe(service, listener);
/*     */     
/*  84 */     List<Service> services = new ArrayList();
/*  85 */     for (int i = 0; i < this.servers.size(); i++) {
/*  86 */       Server server = (Server)this.servers.get(i);
/*  87 */       Service s = new Service(server.getHost(), server.getPort(), service, "providers");
/*     */       
/*  89 */       services.add(s);
/*     */     }
/*     */     
/*  92 */     notify(service, listener, services);
/*     */   }
/*     */   
/*     */   private void check()
/*     */   {
/*  97 */     List<Server> newServers = new ArrayList();
/*     */     
/*  99 */     for (int i = 0; i < this.servers.size(); i++) {
/* 100 */       boolean failure = false;
/* 101 */       Server server = (Server)this.servers.get(i);
/* 102 */       TSocket socket = null;
/*     */       try {
/* 104 */         logger.debug("heartbeat for " + server);
/* 105 */         socket = new TSocket(server.getHost(), server.getPort());
/* 106 */         socket.setTimeout(2000);
/* 107 */         socket.open();
/*     */       } catch (Exception e) {
/* 109 */         failure = true;
/* 110 */         logger.error("heart beat check failure: " + server, e);
/*     */       } finally {
/* 112 */         if ((socket != null) && (socket.isOpen())) {
/* 113 */           socket.close();
/*     */         }
/*     */         
/* 116 */         if (!failure) {
/* 117 */           newServers.add(server);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 122 */     List<Service> services = new ArrayList();
/* 123 */     for (int i = 0; i < newServers.size(); i++) {
/* 124 */       Server server = (Server)newServers.get(i);
/* 125 */       Service s = new Service(server.getHost(), server.getPort(), "*", "providers");
/*     */       
/* 127 */       services.add(s);
/*     */     }
/*     */     
/* 130 */     logger.info("flush service list " + services);
/* 131 */     notify(services);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isAvailable()
/*     */   {
/* 137 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 143 */     super.destroy();
/*     */     try {
/* 145 */       if (this.checkTimer != null) {
/* 146 */         this.checkTimer.cancel(true);
/*     */       }
/*     */     } catch (Throwable t) {
/* 149 */       logger.error("cancel check timer failure", t);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getHeartbeatPeriod()
/*     */   {
/* 155 */     return this.heartbeatPeriod;
/*     */   }
/*     */   
/*     */   public void setHeartbeatPeriod(int heartbeatPeriod)
/*     */   {
/* 160 */     this.heartbeatPeriod = heartbeatPeriod;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/sstatic/StaticRegistry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */