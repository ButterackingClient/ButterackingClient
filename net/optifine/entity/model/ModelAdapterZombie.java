/*    */ package net.optifine.entity.model;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombie;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ 
/*    */ public class ModelAdapterZombie extends ModelAdapterBiped {
/*    */   public ModelAdapterZombie() {
/* 14 */     super(EntityZombie.class, "zombie", 0.5F);
/*    */   }
/*    */   
/*    */   public ModelBase makeModel() {
/* 18 */     return (ModelBase)new ModelZombie();
/*    */   }
/*    */   
/*    */   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
/* 22 */     RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
/* 23 */     RenderZombie renderzombie = new RenderZombie(rendermanager);
/* 24 */     Render.setModelBipedMain((RenderBiped)renderzombie, (ModelBiped)modelBase);
/* 25 */     renderzombie.mainModel = modelBase;
/* 26 */     renderzombie.shadowSize = shadowSize;
/* 27 */     return (IEntityRenderer)renderzombie;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\optifine\entity\model\ModelAdapterZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */