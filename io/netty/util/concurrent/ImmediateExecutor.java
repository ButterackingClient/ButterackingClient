/*    */ package io.netty.util.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Executor;
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
/*    */ public final class ImmediateExecutor
/*    */   implements Executor
/*    */ {
/* 24 */   public static final ImmediateExecutor INSTANCE = new ImmediateExecutor();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(Runnable command) {
/* 32 */     if (command == null) {
/* 33 */       throw new NullPointerException("command");
/*    */     }
/* 35 */     command.run();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\concurrent\ImmediateExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */