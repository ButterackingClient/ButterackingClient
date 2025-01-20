/*    */ package io.netty.util;
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
/*    */ public class IllegalReferenceCountException
/*    */   extends IllegalStateException
/*    */ {
/*    */   private static final long serialVersionUID = -2507492394288153468L;
/*    */   
/*    */   public IllegalReferenceCountException() {}
/*    */   
/*    */   public IllegalReferenceCountException(int refCnt) {
/* 30 */     this("refCnt: " + refCnt);
/*    */   }
/*    */   
/*    */   public IllegalReferenceCountException(int refCnt, int increment) {
/* 34 */     this("refCnt: " + refCnt + ", " + ((increment > 0) ? ("increment: " + increment) : ("decrement: " + -increment)));
/*    */   }
/*    */   
/*    */   public IllegalReferenceCountException(String message) {
/* 38 */     super(message);
/*    */   }
/*    */   
/*    */   public IllegalReferenceCountException(String message, Throwable cause) {
/* 42 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public IllegalReferenceCountException(Throwable cause) {
/* 46 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\IllegalReferenceCountException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */