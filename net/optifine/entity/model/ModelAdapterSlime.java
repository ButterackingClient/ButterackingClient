/*    */ package net.optifine.entity.model;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.model.ModelSlime;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSlime;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.optifine.reflect.Reflector;
/*    */ 
/*    */ public class ModelAdapterSlime extends ModelAdapter {
/*    */   public ModelAdapterSlime() {
/* 14 */     super(EntitySlime.class, "slime", 0.25F);
/*    */   }
/*    */   
/*    */   public ModelBase makeModel() {
/* 18 */     return (ModelBase)new ModelSlime(16);
/*    */   }
/*    */   
/*    */   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
/* 22 */     if (!(model instanceof ModelSlime)) {
/* 23 */       return null;
/*    */     }
/* 25 */     ModelSlime modelslime = (ModelSlime)model;
/* 26 */     return modelPart.equals("body") ? (ModelRenderer)Reflector.getFieldValue(modelslime, Reflector.ModelSlime_ModelRenderers, 0) : (modelPart.equals("left_eye") ? (ModelRenderer)Reflector.getFieldValue(modelslime, Reflector.ModelSlime_ModelRenderers, 1) : (modelPart.equals("right_eye") ? (ModelRenderer)Reflector.getFieldValue(modelslime, Reflector.ModelSlime_ModelRenderers, 2) : (modelPart.equals("mouth") ? (ModelRenderer)Reflector.getFieldValue(modelslime, Reflector.ModelSlime_ModelRenderers, 3) : null)));
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getModelRendererNames() {
/* 31 */     return new String[] { "body", "left_eye", "right_eye", "mouth" };
/*    */   }
/*    */   
/*    */   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
/* 35 */     RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
/* 36 */     RenderSlime renderslime = new RenderSlime(rendermanager, modelBase, shadowSize);
/* 37 */     return (IEntityRenderer)renderslime;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\optifine\entity\model\ModelAdapterSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */