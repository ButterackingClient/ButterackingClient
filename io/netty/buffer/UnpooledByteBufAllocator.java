/*    */ package io.netty.buffer;
/*    */ 
/*    */ import io.netty.util.internal.PlatformDependent;
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
/*    */ public final class UnpooledByteBufAllocator
/*    */   extends AbstractByteBufAllocator
/*    */ {
/* 28 */   public static final UnpooledByteBufAllocator DEFAULT = new UnpooledByteBufAllocator(PlatformDependent.directBufferPreferred());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnpooledByteBufAllocator(boolean preferDirect) {
/* 38 */     super(preferDirect);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ByteBuf newHeapBuffer(int initialCapacity, int maxCapacity) {
/* 43 */     return new UnpooledHeapByteBuf(this, initialCapacity, maxCapacity);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ByteBuf newDirectBuffer(int initialCapacity, int maxCapacity) {
/*    */     ByteBuf buf;
/* 49 */     if (PlatformDependent.hasUnsafe()) {
/* 50 */       buf = new UnpooledUnsafeDirectByteBuf(this, initialCapacity, maxCapacity);
/*    */     } else {
/* 52 */       buf = new UnpooledDirectByteBuf(this, initialCapacity, maxCapacity);
/*    */     } 
/*    */     
/* 55 */     return toLeakAwareBuffer(buf);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDirectBufferPooled() {
/* 60 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\buffer\UnpooledByteBufAllocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */