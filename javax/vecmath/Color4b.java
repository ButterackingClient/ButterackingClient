/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Color4b
/*     */   extends Tuple4b
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -105080578052502155L;
/*     */   
/*     */   public Color4b(byte b1, byte b2, byte b3, byte b4) {
/*  60 */     super(b1, b2, b3, b4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b(byte[] c) {
/*  70 */     super(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b(Color4b c1) {
/*  81 */     super(c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b(Tuple4b t1) {
/*  92 */     super(t1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b(Color color) {
/* 110 */     super((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue(), (byte)color.getAlpha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Color color) {
/* 132 */     this.x = (byte)color.getRed();
/* 133 */     this.y = (byte)color.getGreen();
/* 134 */     this.z = (byte)color.getBlue();
/* 135 */     this.w = (byte)color.getAlpha();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Color get() {
/* 147 */     int r = this.x & 0xFF;
/* 148 */     int g = this.y & 0xFF;
/* 149 */     int b = this.z & 0xFF;
/* 150 */     int a = this.w & 0xFF;
/*     */     
/* 152 */     return new Color(r, g, b, a);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\javax\vecmath\Color4b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */