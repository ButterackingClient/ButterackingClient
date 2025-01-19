/*    */ package io.netty.handler.codec;
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
/*    */ public class CorruptedFrameException
/*    */   extends DecoderException
/*    */ {
/*    */   private static final long serialVersionUID = 3918052232492988408L;
/*    */   
/*    */   public CorruptedFrameException() {}
/*    */   
/*    */   public CorruptedFrameException(String message, Throwable cause) {
/* 36 */     super(message, cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CorruptedFrameException(String message) {
/* 43 */     super(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CorruptedFrameException(Throwable cause) {
/* 50 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\handler\codec\CorruptedFrameException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */