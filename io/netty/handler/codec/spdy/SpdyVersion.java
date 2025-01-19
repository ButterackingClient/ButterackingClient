/*    */ package io.netty.handler.codec.spdy;
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
/*    */ public enum SpdyVersion
/*    */ {
/* 19 */   SPDY_3_1(3, 1);
/*    */   
/*    */   private final int minorVersion;
/*    */   private final int version;
/*    */   
/*    */   SpdyVersion(int version, int minorVersion) {
/* 25 */     this.version = version;
/* 26 */     this.minorVersion = minorVersion;
/*    */   }
/*    */   
/*    */   int getVersion() {
/* 30 */     return this.version;
/*    */   }
/*    */   
/*    */   int getMinorVersion() {
/* 34 */     return this.minorVersion;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\handler\codec\spdy\SpdyVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */