/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ 
/*     */ 
/*     */ public class ItemCameraTransforms
/*     */ {
/*  14 */   public static final ItemCameraTransforms DEFAULT = new ItemCameraTransforms();
/*  15 */   public static float field_181690_b = 0.0F;
/*  16 */   public static float field_181691_c = 0.0F;
/*  17 */   public static float field_181692_d = 0.0F;
/*  18 */   public static float field_181693_e = 0.0F;
/*  19 */   public static float field_181694_f = 0.0F;
/*  20 */   public static float field_181695_g = 0.0F;
/*  21 */   public static float field_181696_h = 0.0F;
/*  22 */   public static float field_181697_i = 0.0F;
/*  23 */   public static float field_181698_j = 0.0F;
/*     */   public final ItemTransformVec3f thirdPerson;
/*     */   public final ItemTransformVec3f firstPerson;
/*     */   public final ItemTransformVec3f head;
/*     */   public final ItemTransformVec3f gui;
/*     */   public final ItemTransformVec3f ground;
/*     */   public final ItemTransformVec3f fixed;
/*     */   
/*     */   private ItemCameraTransforms() {
/*  32 */     this(ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT);
/*     */   }
/*     */   
/*     */   public ItemCameraTransforms(ItemCameraTransforms transforms) {
/*  36 */     this.thirdPerson = transforms.thirdPerson;
/*  37 */     this.firstPerson = transforms.firstPerson;
/*  38 */     this.head = transforms.head;
/*  39 */     this.gui = transforms.gui;
/*  40 */     this.ground = transforms.ground;
/*  41 */     this.fixed = transforms.fixed;
/*     */   }
/*     */   
/*     */   public ItemCameraTransforms(ItemTransformVec3f thirdPersonIn, ItemTransformVec3f firstPersonIn, ItemTransformVec3f headIn, ItemTransformVec3f guiIn, ItemTransformVec3f groundIn, ItemTransformVec3f fixedIn) {
/*  45 */     this.thirdPerson = thirdPersonIn;
/*  46 */     this.firstPerson = firstPersonIn;
/*  47 */     this.head = headIn;
/*  48 */     this.gui = guiIn;
/*  49 */     this.ground = groundIn;
/*  50 */     this.fixed = fixedIn;
/*     */   }
/*     */   
/*     */   public void applyTransform(TransformType type) {
/*  54 */     ItemTransformVec3f itemtransformvec3f = getTransform(type);
/*     */     
/*  56 */     if (itemtransformvec3f != ItemTransformVec3f.DEFAULT) {
/*  57 */       GlStateManager.translate(itemtransformvec3f.translation.x + field_181690_b, itemtransformvec3f.translation.y + field_181691_c, itemtransformvec3f.translation.z + field_181692_d);
/*  58 */       GlStateManager.rotate(itemtransformvec3f.rotation.y + field_181694_f, 0.0F, 1.0F, 0.0F);
/*  59 */       GlStateManager.rotate(itemtransformvec3f.rotation.x + field_181693_e, 1.0F, 0.0F, 0.0F);
/*  60 */       GlStateManager.rotate(itemtransformvec3f.rotation.z + field_181695_g, 0.0F, 0.0F, 1.0F);
/*  61 */       GlStateManager.scale(itemtransformvec3f.scale.x + field_181696_h, itemtransformvec3f.scale.y + field_181697_i, itemtransformvec3f.scale.z + field_181698_j);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemTransformVec3f getTransform(TransformType type) {
/*  66 */     switch (type) {
/*     */       case THIRD_PERSON:
/*  68 */         return this.thirdPerson;
/*     */       
/*     */       case null:
/*  71 */         return this.firstPerson;
/*     */       
/*     */       case HEAD:
/*  74 */         return this.head;
/*     */       
/*     */       case GUI:
/*  77 */         return this.gui;
/*     */       
/*     */       case GROUND:
/*  80 */         return this.ground;
/*     */       
/*     */       case FIXED:
/*  83 */         return this.fixed;
/*     */     } 
/*     */     
/*  86 */     return ItemTransformVec3f.DEFAULT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_181687_c(TransformType type) {
/*  91 */     return !getTransform(type).equals(ItemTransformVec3f.DEFAULT);
/*     */   }
/*     */   
/*     */   static class Deserializer implements JsonDeserializer<ItemCameraTransforms> {
/*     */     public ItemCameraTransforms deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
/*  96 */       JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
/*  97 */       ItemTransformVec3f itemtransformvec3f = func_181683_a(p_deserialize_3_, jsonobject, "thirdperson");
/*  98 */       ItemTransformVec3f itemtransformvec3f1 = func_181683_a(p_deserialize_3_, jsonobject, "firstperson");
/*  99 */       ItemTransformVec3f itemtransformvec3f2 = func_181683_a(p_deserialize_3_, jsonobject, "head");
/* 100 */       ItemTransformVec3f itemtransformvec3f3 = func_181683_a(p_deserialize_3_, jsonobject, "gui");
/* 101 */       ItemTransformVec3f itemtransformvec3f4 = func_181683_a(p_deserialize_3_, jsonobject, "ground");
/* 102 */       ItemTransformVec3f itemtransformvec3f5 = func_181683_a(p_deserialize_3_, jsonobject, "fixed");
/* 103 */       return new ItemCameraTransforms(itemtransformvec3f, itemtransformvec3f1, itemtransformvec3f2, itemtransformvec3f3, itemtransformvec3f4, itemtransformvec3f5);
/*     */     }
/*     */     
/*     */     private ItemTransformVec3f func_181683_a(JsonDeserializationContext p_181683_1_, JsonObject p_181683_2_, String p_181683_3_) {
/* 107 */       return p_181683_2_.has(p_181683_3_) ? (ItemTransformVec3f)p_181683_1_.deserialize(p_181683_2_.get(p_181683_3_), ItemTransformVec3f.class) : ItemTransformVec3f.DEFAULT;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum TransformType {
/* 112 */     NONE,
/* 113 */     THIRD_PERSON,
/* 114 */     FIRST_PERSON,
/* 115 */     HEAD,
/* 116 */     GUI,
/* 117 */     GROUND,
/* 118 */     FIXED;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\renderer\block\model\ItemCameraTransforms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */