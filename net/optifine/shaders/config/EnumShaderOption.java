/*    */ package net.optifine.shaders.config;
/*    */ 
/*    */ public enum EnumShaderOption {
/*  4 */   ANTIALIASING("of.options.shaders.ANTIALIASING", "antialiasingLevel", "0"),
/*  5 */   NORMAL_MAP("of.options.shaders.NORMAL_MAP", "normalMapEnabled", "true"),
/*  6 */   SPECULAR_MAP("of.options.shaders.SPECULAR_MAP", "specularMapEnabled", "true"),
/*  7 */   RENDER_RES_MUL("of.options.shaders.RENDER_RES_MUL", "renderResMul", "1.0"),
/*  8 */   SHADOW_RES_MUL("of.options.shaders.SHADOW_RES_MUL", "shadowResMul", "1.0"),
/*  9 */   HAND_DEPTH_MUL("of.options.shaders.HAND_DEPTH_MUL", "handDepthMul", "0.125"),
/* 10 */   CLOUD_SHADOW("of.options.shaders.CLOUD_SHADOW", "cloudShadow", "true"),
/* 11 */   OLD_HAND_LIGHT("of.options.shaders.OLD_HAND_LIGHT", "oldHandLight", "default"),
/* 12 */   OLD_LIGHTING("of.options.shaders.OLD_LIGHTING", "oldLighting", "default"),
/* 13 */   SHADER_PACK("of.options.shaders.SHADER_PACK", "shaderPack", ""),
/* 14 */   TWEAK_BLOCK_DAMAGE("of.options.shaders.TWEAK_BLOCK_DAMAGE", "tweakBlockDamage", "false"),
/* 15 */   SHADOW_CLIP_FRUSTRUM("of.options.shaders.SHADOW_CLIP_FRUSTRUM", "shadowClipFrustrum", "true"),
/* 16 */   TEX_MIN_FIL_B("of.options.shaders.TEX_MIN_FIL_B", "TexMinFilB", "0"),
/* 17 */   TEX_MIN_FIL_N("of.options.shaders.TEX_MIN_FIL_N", "TexMinFilN", "0"),
/* 18 */   TEX_MIN_FIL_S("of.options.shaders.TEX_MIN_FIL_S", "TexMinFilS", "0"),
/* 19 */   TEX_MAG_FIL_B("of.options.shaders.TEX_MAG_FIL_B", "TexMagFilB", "0"),
/* 20 */   TEX_MAG_FIL_N("of.options.shaders.TEX_MAG_FIL_N", "TexMagFilN", "0"),
/* 21 */   TEX_MAG_FIL_S("of.options.shaders.TEX_MAG_FIL_S", "TexMagFilS", "0");
/*    */   
/* 23 */   private String resourceKey = null;
/* 24 */   private String propertyKey = null;
/* 25 */   private String valueDefault = null;
/*    */   
/*    */   EnumShaderOption(String resourceKey, String propertyKey, String valueDefault) {
/* 28 */     this.resourceKey = resourceKey;
/* 29 */     this.propertyKey = propertyKey;
/* 30 */     this.valueDefault = valueDefault;
/*    */   }
/*    */   
/*    */   public String getResourceKey() {
/* 34 */     return this.resourceKey;
/*    */   }
/*    */   
/*    */   public String getPropertyKey() {
/* 38 */     return this.propertyKey;
/*    */   }
/*    */   
/*    */   public String getValueDefault() {
/* 42 */     return this.valueDefault;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\optifine\shaders\config\EnumShaderOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */