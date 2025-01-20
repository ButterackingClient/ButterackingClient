/*    */ package io.netty.channel.udt;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufHolder;
/*    */ import io.netty.buffer.DefaultByteBufHolder;
/*    */ import io.netty.util.ReferenceCounted;
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
/*    */ public final class UdtMessage
/*    */   extends DefaultByteBufHolder
/*    */ {
/*    */   public UdtMessage(ByteBuf data) {
/* 31 */     super(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public UdtMessage copy() {
/* 36 */     return new UdtMessage(content().copy());
/*    */   }
/*    */ 
/*    */   
/*    */   public UdtMessage duplicate() {
/* 41 */     return new UdtMessage(content().duplicate());
/*    */   }
/*    */ 
/*    */   
/*    */   public UdtMessage retain() {
/* 46 */     super.retain();
/* 47 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public UdtMessage retain(int increment) {
/* 52 */     super.retain(increment);
/* 53 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\channe\\udt\UdtMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */