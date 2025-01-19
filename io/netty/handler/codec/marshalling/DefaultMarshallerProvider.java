/*    */ package io.netty.handler.codec.marshalling;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import org.jboss.marshalling.Marshaller;
/*    */ import org.jboss.marshalling.MarshallerFactory;
/*    */ import org.jboss.marshalling.MarshallingConfiguration;
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
/*    */ 
/*    */ 
/*    */ public class DefaultMarshallerProvider
/*    */   implements MarshallerProvider
/*    */ {
/*    */   private final MarshallerFactory factory;
/*    */   private final MarshallingConfiguration config;
/*    */   
/*    */   public DefaultMarshallerProvider(MarshallerFactory factory, MarshallingConfiguration config) {
/* 40 */     this.factory = factory;
/* 41 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public Marshaller getMarshaller(ChannelHandlerContext ctx) throws Exception {
/* 46 */     return this.factory.createMarshaller(this.config);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\handler\codec\marshalling\DefaultMarshallerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */