/*    */ package io.netty.handler.codec.spdy;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
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
/*    */ abstract class SpdyHeaderBlockDecoder
/*    */ {
/*    */   static SpdyHeaderBlockDecoder newInstance(SpdyVersion spdyVersion, int maxHeaderSize) {
/* 23 */     return new SpdyHeaderBlockZlibDecoder(spdyVersion, maxHeaderSize);
/*    */   }
/*    */   
/*    */   abstract void decode(ByteBuf paramByteBuf, SpdyHeadersFrame paramSpdyHeadersFrame) throws Exception;
/*    */   
/*    */   abstract void endHeaderBlock(SpdyHeadersFrame paramSpdyHeadersFrame) throws Exception;
/*    */   
/*    */   abstract void end();
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\handler\codec\spdy\SpdyHeaderBlockDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */