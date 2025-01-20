/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelCreeper extends ModelBase {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer creeperArmor;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer leg1;
/*    */   public ModelRenderer leg2;
/*    */   public ModelRenderer leg3;
/*    */   public ModelRenderer leg4;
/*    */   
/*    */   public ModelCreeper() {
/* 16 */     this(0.0F);
/*    */   }
/*    */   
/*    */   public ModelCreeper(float p_i46366_1_) {
/* 20 */     int i = 6;
/* 21 */     this.head = new ModelRenderer(this, 0, 0);
/* 22 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_);
/* 23 */     this.head.setRotationPoint(0.0F, i, 0.0F);
/* 24 */     this.creeperArmor = new ModelRenderer(this, 32, 0);
/* 25 */     this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_ + 0.5F);
/* 26 */     this.creeperArmor.setRotationPoint(0.0F, i, 0.0F);
/* 27 */     this.body = new ModelRenderer(this, 16, 16);
/* 28 */     this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46366_1_);
/* 29 */     this.body.setRotationPoint(0.0F, i, 0.0F);
/* 30 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 31 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 32 */     this.leg1.setRotationPoint(-2.0F, (12 + i), 4.0F);
/* 33 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 34 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 35 */     this.leg2.setRotationPoint(2.0F, (12 + i), 4.0F);
/* 36 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 37 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 38 */     this.leg3.setRotationPoint(-2.0F, (12 + i), -4.0F);
/* 39 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 40 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 41 */     this.leg4.setRotationPoint(2.0F, (12 + i), -4.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
/* 48 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
/* 49 */     this.head.render(scale);
/* 50 */     this.body.render(scale);
/* 51 */     this.leg1.render(scale);
/* 52 */     this.leg2.render(scale);
/* 53 */     this.leg3.render(scale);
/* 54 */     this.leg4.render(scale);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
/* 63 */     this.head.rotateAngleY = netHeadYaw / 57.295776F;
/* 64 */     this.head.rotateAngleX = headPitch / 57.295776F;
/* 65 */     this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
/* 66 */     this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
/* 67 */     this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
/* 68 */     this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\model\ModelCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */