/*    */ package com.xman.common.rpc.client.thrift;
/*    */ 
/*    */ import com.xman.common.log.ILog;
/*    */ import com.xman.common.log.LogFactory;
/*    */ import com.xman.common.rpc.RpcException;
/*    */ import com.xman.common.rpc.Server;
/*    */ import com.xman.common.rpc.pool.ConnFactory;
/*    */ import org.apache.thrift.transport.TSocket;
/*    */ 
/*    */ public class ThriftTSocketFactory extends ConnFactory<TSocket>
/*    */ {
/* 12 */   private static final ILog logger = LogFactory.getLog(ThriftTSocketFactory.class);
/*    */   
/*    */   public TSocket makeObject(Server server) throws Exception
/*    */   {
/* 16 */     logger.debug("make object: " + server);
/*    */     
/* 18 */     TSocket socket = new TSocket(server.getHost(), server.getPort());
/* 19 */     socket.setTimeout(1000);
/* 20 */     socket.open();
/*    */     
/* 22 */     return socket;
/*    */   }
/*    */   
/*    */   public void destroyObject(TSocket t) throws Exception {
/* 26 */     logger.debug("destroy object");
/* 27 */     if (t.isOpen()) {
/* 28 */       t.close();
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean validateObject(TSocket t) {
/* 33 */     return t.isOpen();
/*    */   }
/*    */   
/*    */   public void activateObject(TSocket t) throws Exception
/*    */   {
/* 38 */     if (!t.isOpen()) {
/* 39 */       throw new RpcException("socket closed");
/*    */     }
/*    */   }
/*    */   
/*    */   public void passivateObject(TSocket t) throws Exception
/*    */   {
/* 45 */     if (!t.isOpen()) {
/* 46 */       throw new RpcException("socket closed");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/client/thrift/ThriftTSocketFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */