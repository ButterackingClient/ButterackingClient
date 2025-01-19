/*    */ package net.minecraft.network;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.ShortBufferException;
/*    */ 
/*    */ public class NettyEncryptingDecoder
/*    */   extends MessageToMessageDecoder<ByteBuf> {
/*    */   private final NettyEncryptionTranslator decryptionCodec;
/*    */   
/*    */   public NettyEncryptingDecoder(Cipher cipher) {
/* 15 */     this.decryptionCodec = new NettyEncryptionTranslator(cipher);
/*    */   }
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws ShortBufferException, Exception {
/* 19 */     p_decode_3_.add(this.decryptionCodec.decipher(p_decode_1_, p_decode_2_));
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\network\NettyEncryptingDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */