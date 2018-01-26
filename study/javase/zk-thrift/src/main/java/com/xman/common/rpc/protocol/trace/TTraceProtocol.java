/*    */ package com.xman.common.rpc.protocol.trace;
/*    */ 
/*    */ import com.xman.common.log.ILog;
/*    */ import com.xman.common.log.LogFactory;
/*    */ import com.xman.common.rpc.filter.trace.TraceData;
/*    */ import com.xman.common.rpc.invoke.RpcContext;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.apache.thrift.TException;
/*    */ import org.apache.thrift.protocol.TMessage;
/*    */ import org.apache.thrift.protocol.TProtocol;
/*    */ import org.apache.thrift.protocol.TProtocolDecorator;
/*    */ import org.apache.thrift.protocol.TProtocolFactory;
/*    */ import org.apache.thrift.transport.TTransport;
/*    */ import org.nustaq.serialization.FSTConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TTraceProtocol
/*    */   extends TProtocolDecorator
/*    */ {
/* 22 */   private static final ILog logger = LogFactory.getLog(TTraceProtocol.class);
/*    */   public static final byte HEADER_PROTOCOL_ID = -120;
/*    */   
/*    */   public TTraceProtocol(TProtocol protocol)
/*    */   {
/* 27 */     super(protocol);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void writeMessageBegin(TMessage tMessage)
/*    */     throws TException
/*    */   {
/* 36 */     writeContext();
/* 37 */     super.writeMessageBegin(tMessage);
/*    */   }
/*    */   
/*    */   private void writeContext() throws TException
/*    */   {
/* 42 */     ByteBuffer b = serialize(RpcContext.getContext());
/* 43 */     logger.debug("ctx size: {}" + b.remaining());
/* 44 */     writeByte((byte)-120);
/* 45 */     writeBinary(b);
/*    */   }
/*    */   
/* 48 */   static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
/*    */   
/* 50 */   static { conf.registerClass(new Class[] { RpcContext.class, TraceData.class }); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private ByteBuffer serialize(Object obj)
/*    */   {
/* 65 */     byte[] buffer = conf.asByteArray(obj);
/* 66 */     return ByteBuffer.wrap(buffer);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements TProtocolFactory
/*    */   {
/*    */     private static final long serialVersionUID = -8248763746828512916L;
/*    */     private final TProtocolFactory actualProtocolFactory;
/*    */     
/*    */     public Factory(TProtocolFactory protocolFactory)
/*    */     {
/* 77 */       this.actualProtocolFactory = protocolFactory;
/*    */     }
/*    */     
/*    */     public TProtocol getProtocol(TTransport trans)
/*    */     {
/* 82 */       return new TTraceProtocol(this.actualProtocolFactory.getProtocol(trans));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/protocol/trace/TTraceProtocol.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */