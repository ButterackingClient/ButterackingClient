/*     */ package net.minecraft.realms;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ 
/*     */ public class RealmsBufferBuilder
/*     */ {
/*     */   private WorldRenderer b;
/*     */   
/*     */   public RealmsBufferBuilder(WorldRenderer p_i46442_1_) {
/*  12 */     this.b = p_i46442_1_;
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder from(WorldRenderer p_from_1_) {
/*  16 */     this.b = p_from_1_;
/*  17 */     return this;
/*     */   }
/*     */   
/*     */   public void sortQuads(float p_sortQuads_1_, float p_sortQuads_2_, float p_sortQuads_3_) {
/*  21 */     this.b.sortVertexData(p_sortQuads_1_, p_sortQuads_2_, p_sortQuads_3_);
/*     */   }
/*     */   
/*     */   public void fixupQuadColor(int p_fixupQuadColor_1_) {
/*  25 */     this.b.putColor4(p_fixupQuadColor_1_);
/*     */   }
/*     */   
/*     */   public ByteBuffer getBuffer() {
/*  29 */     return this.b.getByteBuffer();
/*     */   }
/*     */   
/*     */   public void postNormal(float p_postNormal_1_, float p_postNormal_2_, float p_postNormal_3_) {
/*  33 */     this.b.putNormal(p_postNormal_1_, p_postNormal_2_, p_postNormal_3_);
/*     */   }
/*     */   
/*     */   public int getDrawMode() {
/*  37 */     return this.b.getDrawMode();
/*     */   }
/*     */   
/*     */   public void offset(double p_offset_1_, double p_offset_3_, double p_offset_5_) {
/*  41 */     this.b.setTranslation(p_offset_1_, p_offset_3_, p_offset_5_);
/*     */   }
/*     */   
/*     */   public void restoreState(WorldRenderer.State p_restoreState_1_) {
/*  45 */     this.b.setVertexState(p_restoreState_1_);
/*     */   }
/*     */   
/*     */   public void endVertex() {
/*  49 */     this.b.endVertex();
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder normal(float p_normal_1_, float p_normal_2_, float p_normal_3_) {
/*  53 */     return from(this.b.normal(p_normal_1_, p_normal_2_, p_normal_3_));
/*     */   }
/*     */   
/*     */   public void end() {
/*  57 */     this.b.finishDrawing();
/*     */   }
/*     */   
/*     */   public void begin(int p_begin_1_, VertexFormat p_begin_2_) {
/*  61 */     this.b.begin(p_begin_1_, p_begin_2_);
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder color(int p_color_1_, int p_color_2_, int p_color_3_, int p_color_4_) {
/*  65 */     return from(this.b.color(p_color_1_, p_color_2_, p_color_3_, p_color_4_));
/*     */   }
/*     */   
/*     */   public void faceTex2(int p_faceTex2_1_, int p_faceTex2_2_, int p_faceTex2_3_, int p_faceTex2_4_) {
/*  69 */     this.b.putBrightness4(p_faceTex2_1_, p_faceTex2_2_, p_faceTex2_3_, p_faceTex2_4_);
/*     */   }
/*     */   
/*     */   public void postProcessFacePosition(double p_postProcessFacePosition_1_, double p_postProcessFacePosition_3_, double p_postProcessFacePosition_5_) {
/*  73 */     this.b.putPosition(p_postProcessFacePosition_1_, p_postProcessFacePosition_3_, p_postProcessFacePosition_5_);
/*     */   }
/*     */   
/*     */   public void fixupVertexColor(float p_fixupVertexColor_1_, float p_fixupVertexColor_2_, float p_fixupVertexColor_3_, int p_fixupVertexColor_4_) {
/*  77 */     this.b.putColorRGB_F(p_fixupVertexColor_1_, p_fixupVertexColor_2_, p_fixupVertexColor_3_, p_fixupVertexColor_4_);
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder color(float p_color_1_, float p_color_2_, float p_color_3_, float p_color_4_) {
/*  81 */     return from(this.b.color(p_color_1_, p_color_2_, p_color_3_, p_color_4_));
/*     */   }
/*     */   
/*     */   public RealmsVertexFormat getVertexFormat() {
/*  85 */     return new RealmsVertexFormat(this.b.getVertexFormat());
/*     */   }
/*     */   
/*     */   public void faceTint(float p_faceTint_1_, float p_faceTint_2_, float p_faceTint_3_, int p_faceTint_4_) {
/*  89 */     this.b.putColorMultiplier(p_faceTint_1_, p_faceTint_2_, p_faceTint_3_, p_faceTint_4_);
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder tex2(int p_tex2_1_, int p_tex2_2_) {
/*  93 */     return from(this.b.lightmap(p_tex2_1_, p_tex2_2_));
/*     */   }
/*     */   
/*     */   public void putBulkData(int[] p_putBulkData_1_) {
/*  97 */     this.b.addVertexData(p_putBulkData_1_);
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder tex(double p_tex_1_, double p_tex_3_) {
/* 101 */     return from(this.b.tex(p_tex_1_, p_tex_3_));
/*     */   }
/*     */   
/*     */   public int getVertexCount() {
/* 105 */     return this.b.getVertexCount();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 109 */     this.b.reset();
/*     */   }
/*     */   
/*     */   public RealmsBufferBuilder vertex(double p_vertex_1_, double p_vertex_3_, double p_vertex_5_) {
/* 113 */     return from(this.b.pos(p_vertex_1_, p_vertex_3_, p_vertex_5_));
/*     */   }
/*     */   
/*     */   public void fixupQuadColor(float p_fixupQuadColor_1_, float p_fixupQuadColor_2_, float p_fixupQuadColor_3_) {
/* 117 */     this.b.putColorRGB_F4(p_fixupQuadColor_1_, p_fixupQuadColor_2_, p_fixupQuadColor_3_);
/*     */   }
/*     */   
/*     */   public void noColor() {
/* 121 */     this.b.noColor();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\realms\RealmsBufferBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */