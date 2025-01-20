/*    */ package io.netty.util.internal;
/*    */ 
/*    */ import io.netty.util.Recycler;
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
/*    */ public abstract class RecyclableMpscLinkedQueueNode<T>
/*    */   extends MpscLinkedQueueNode<T>
/*    */ {
/*    */   private final Recycler.Handle handle;
/*    */   
/*    */   protected RecyclableMpscLinkedQueueNode(Recycler.Handle handle) {
/* 29 */     if (handle == null) {
/* 30 */       throw new NullPointerException("handle");
/*    */     }
/* 32 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   
/*    */   final void unlink() {
/* 37 */     super.unlink();
/* 38 */     recycle(this.handle);
/*    */   }
/*    */   
/*    */   protected abstract void recycle(Recycler.Handle paramHandle);
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\internal\RecyclableMpscLinkedQueueNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */