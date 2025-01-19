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
/*     */ public class Color4f
/*     */   extends Tuple4f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 8577680141580006740L;
/*     */   
/*     */   public Color4f(float x, float y, float z, float w) {
/*  56 */     super(x, y, z, w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4f(float[] c) {
/*  66 */     super(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4f(Color4f c1) {
/*  76 */     super(c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4f(Tuple4f t1) {
/*  86 */     super(t1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4f(Tuple4d t1) {
/*  96 */     super(t1);
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
/*     */   public Color4f(Color color) {
/* 114 */     super(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
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
/*     */   public Color4f() {}
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
/* 136 */     this.x = color.getRed() / 255.0F;
/* 137 */     this.y = color.getGreen() / 255.0F;
/* 138 */     this.z = color.getBlue() / 255.0F;
/* 139 */     this.w = color.getAlpha() / 255.0F;
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
/* 151 */     int r = Math.round(this.x * 255.0F);
/* 152 */     int g = Math.round(this.y * 255.0F);
/* 153 */     int b = Math.round(this.z * 255.0F);
/* 154 */     int a = Math.round(this.w * 255.0F);
/*     */     
/* 156 */     return new Color(r, g, b, a);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\javax\vecmath\Color4f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */