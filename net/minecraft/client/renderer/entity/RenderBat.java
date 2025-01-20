/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBat;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityBat;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderBat extends RenderLiving<EntityBat> {
/* 10 */   private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
/*    */   
/*    */   public RenderBat(RenderManager renderManagerIn) {
/* 13 */     super(renderManagerIn, (ModelBase)new ModelBat(), 0.25F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityBat entity) {
/* 20 */     return batTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityBat entitylivingbaseIn, float partialTickTime) {
/* 28 */     GlStateManager.scale(0.35F, 0.35F, 0.35F);
/*    */   }
/*    */   
/*    */   protected void rotateCorpse(EntityBat bat, float p_77043_2_, float p_77043_3_, float partialTicks) {
/* 32 */     if (!bat.getIsBatHanging()) {
/* 33 */       GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
/*    */     } else {
/* 35 */       GlStateManager.translate(0.0F, -0.1F, 0.0F);
/*    */     } 
/*    */     
/* 38 */     super.rotateCorpse(bat, p_77043_2_, p_77043_3_, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\renderer\entity\RenderBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */