/*    */ package com.xman.common.rpc.protocol.trace;
/*    */ 
/*    */ import com.xman.common.log.ILog;
/*    */ import com.xman.common.log.LogFactory;
/*    */ import com.xman.common.rpc.filter.trace.TraceData;
/*    */ import com.xman.common.rpc.invoke.RpcContext;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.apache.commons.lang.ArrayUtils;
/*    */ import org.apache.thrift.TException;
/*    */ import org.apache.thrift.TProcessor;
/*    */ import org.apache.thrift.protocol.TProtocol;
/*    */ import org.apache.thrift.transport.TTransport;
/*    */ import org.nustaq.serialization.FSTConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TTraceProcessor
/*    */   implements TProcessor
/*    */ {
/* 21 */   private static final ILog logger = LogFactory.getLog(TTraceProcessor.class);
/*    */   private TProcessor actualProcessor;
/*    */   
/*    */   public TTraceProcessor(TProcessor tProcessor)
/*    */   {
/* 26 */     this.actualProcessor = tProcessor;
/*    */   }
/*    */   
/*    */   public boolean process(TProtocol in, TProtocol out) throws TException
/*    */   {
/*    */     try
/*    */     {
/* 33 */       TTransport transport = in.getTransport();
/* 34 */       byte b = in.readByte();
/* 35 */       RpcContext ctx; if (b == -120) {
/* 36 */         ctx = deserialize(in.readBinary());
/* 37 */         if (ctx == null) {
/* 38 */           logger.error("no context, unexcepted");
/* 39 */           ctx = RpcContext.getContext();
/*    */         } else {
/* 41 */           RpcContext.setContext(ctx);
/*    */         }
/*    */       } else {
/* 44 */         transport.pushBack(new byte[] { b });
/*    */       }
/* 46 */       return this.actualProcessor.process(in, out);
/*    */     } finally {
/* 48 */       RpcContext.removeContext();
/*    */     }
/*    */   }
/*    */   
/* 52 */   static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
/*    */   
/* 54 */   static { conf.registerClass(new Class[] { RpcContext.class, TraceData.class }); }
/*    */   
/*    */ 
/*    */   private RpcContext deserialize(ByteBuffer buf)
/*    */   {
/* 59 */     if (buf == null) {
/* 60 */       return null;
/*    */     }
/*    */     
/* 63 */     logger.debug("read ctx, offset:{}, position:{}, remaining:{}, limit:{}" + buf.arrayOffset() + " ," + buf.position() + " ," + buf.remaining() + " ," + buf.limit());
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 71 */     int startIndex = buf.arrayOffset() + buf.position();
/* 72 */     return (RpcContext)conf.asObject(ArrayUtils.subarray(buf.array(), startIndex, startIndex + buf.remaining()));
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/protocol/trace/TTraceProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */