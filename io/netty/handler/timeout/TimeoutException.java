/*    */ package io.netty.handler.timeout;
/*    */ 
/*    */ import io.netty.channel.ChannelException;
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
/*    */ public class TimeoutException
/*    */   extends ChannelException
/*    */ {
/*    */   private static final long serialVersionUID = 4673641882869672533L;
/*    */   
/*    */   public Throwable fillInStackTrace() {
/* 32 */     return (Throwable)this;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\timeout\TimeoutException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */