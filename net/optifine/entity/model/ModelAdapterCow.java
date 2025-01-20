/*    */ package net.optifine.entity.model;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelCow;
/*    */ import net.minecraft.client.renderer.entity.RenderCow;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.passive.EntityCow;
/*    */ 
/*    */ public class ModelAdapterCow extends ModelAdapterQuadruped {
/*    */   public ModelAdapterCow() {
/* 12 */     super(EntityCow.class, "cow", 0.7F);
/*    */   }
/*    */   
/*    */   public ModelBase makeModel() {
/* 16 */     return (ModelBase)new ModelCow();
/*    */   }
/*    */   
/*    */   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
/* 20 */     RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
/* 21 */     return (IEntityRenderer)new RenderCow(rendermanager, modelBase, shadowSize);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\optifine\entity\model\ModelAdapterCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */