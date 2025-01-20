/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
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
/*     */ public class EntityFX
/*     */   extends Entity
/*     */ {
/*     */   protected int particleTextureIndexX;
/*     */   protected int particleTextureIndexY;
/*     */   protected float particleTextureJitterX;
/*     */   protected float particleTextureJitterY;
/*     */   protected int particleAge;
/*     */   protected int particleMaxAge;
/*     */   protected float particleScale;
/*     */   protected float particleGravity;
/*     */   protected float particleRed;
/*     */   protected float particleGreen;
/*     */   protected float particleBlue;
/*     */   protected float particleAlpha;
/*     */   protected TextureAtlasSprite particleIcon;
/*     */   public static double interpPosX;
/*     */   public static double interpPosY;
/*     */   public static double interpPosZ;
/*     */   
/*     */   protected EntityFX(World worldIn, double posXIn, double posYIn, double posZIn) {
/*  50 */     super(worldIn);
/*  51 */     this.particleAlpha = 1.0F;
/*  52 */     setSize(0.2F, 0.2F);
/*  53 */     setPosition(posXIn, posYIn, posZIn);
/*  54 */     this.lastTickPosX = this.prevPosX = posXIn;
/*  55 */     this.lastTickPosY = this.prevPosY = posYIn;
/*  56 */     this.lastTickPosZ = this.prevPosZ = posZIn;
/*  57 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/*  58 */     this.particleTextureJitterX = this.rand.nextFloat() * 3.0F;
/*  59 */     this.particleTextureJitterY = this.rand.nextFloat() * 3.0F;
/*  60 */     this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
/*  61 */     this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
/*  62 */     this.particleAge = 0;
/*     */   }
/*     */   
/*     */   public EntityFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
/*  66 */     this(worldIn, xCoordIn, yCoordIn, zCoordIn);
/*  67 */     this.motionX = xSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  68 */     this.motionY = ySpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  69 */     this.motionZ = zSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  70 */     float f = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
/*  71 */     float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*  72 */     this.motionX = this.motionX / f1 * f * 0.4000000059604645D;
/*  73 */     this.motionY = this.motionY / f1 * f * 0.4000000059604645D + 0.10000000149011612D;
/*  74 */     this.motionZ = this.motionZ / f1 * f * 0.4000000059604645D;
/*     */   }
/*     */   
/*     */   public EntityFX multiplyVelocity(float multiplier) {
/*  78 */     this.motionX *= multiplier;
/*  79 */     this.motionY = (this.motionY - 0.10000000149011612D) * multiplier + 0.10000000149011612D;
/*  80 */     this.motionZ *= multiplier;
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public EntityFX multipleParticleScaleBy(float scale) {
/*  85 */     setSize(0.2F * scale, 0.2F * scale);
/*  86 */     this.particleScale *= scale;
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public void setRBGColorF(float particleRedIn, float particleGreenIn, float particleBlueIn) {
/*  91 */     this.particleRed = particleRedIn;
/*  92 */     this.particleGreen = particleGreenIn;
/*  93 */     this.particleBlue = particleBlueIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaF(float alpha) {
/* 100 */     if (this.particleAlpha == 1.0F && alpha < 1.0F) {
/* 101 */       (Minecraft.getMinecraft()).effectRenderer.moveToAlphaLayer(this);
/* 102 */     } else if (this.particleAlpha < 1.0F && alpha == 1.0F) {
/* 103 */       (Minecraft.getMinecraft()).effectRenderer.moveToNoAlphaLayer(this);
/*     */     } 
/*     */     
/* 106 */     this.particleAlpha = alpha;
/*     */   }
/*     */   
/*     */   public float getRedColorF() {
/* 110 */     return this.particleRed;
/*     */   }
/*     */   
/*     */   public float getGreenColorF() {
/* 114 */     return this.particleGreen;
/*     */   }
/*     */   
/*     */   public float getBlueColorF() {
/* 118 */     return this.particleBlue;
/*     */   }
/*     */   
/*     */   public float getAlpha() {
/* 122 */     return this.particleAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 140 */     this.prevPosX = this.posX;
/* 141 */     this.prevPosY = this.posY;
/* 142 */     this.prevPosZ = this.posZ;
/*     */     
/* 144 */     if (this.particleAge++ >= this.particleMaxAge) {
/* 145 */       setDead();
/*     */     }
/*     */     
/* 148 */     this.motionY -= 0.04D * this.particleGravity;
/* 149 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 150 */     this.motionX *= 0.9800000190734863D;
/* 151 */     this.motionY *= 0.9800000190734863D;
/* 152 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/* 154 */     if (this.onGround) {
/* 155 */       this.motionX *= 0.699999988079071D;
/* 156 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
/* 164 */     float f = this.particleTextureIndexX / 16.0F;
/* 165 */     float f1 = f + 0.0624375F;
/* 166 */     float f2 = this.particleTextureIndexY / 16.0F;
/* 167 */     float f3 = f2 + 0.0624375F;
/* 168 */     float f4 = 0.1F * this.particleScale;
/*     */     
/* 170 */     if (this.particleIcon != null) {
/* 171 */       f = this.particleIcon.getMinU();
/* 172 */       f1 = this.particleIcon.getMaxU();
/* 173 */       f2 = this.particleIcon.getMinV();
/* 174 */       f3 = this.particleIcon.getMaxV();
/*     */     } 
/*     */     
/* 177 */     float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
/* 178 */     float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
/* 179 */     float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
/* 180 */     int i = getBrightnessForRender(partialTicks);
/* 181 */     int j = i >> 16 & 0xFFFF;
/* 182 */     int k = i & 0xFFFF;
/* 183 */     worldRendererIn.pos((f5 - rotationX * f4 - rotationXY * f4), (f6 - rotationZ * f4), (f7 - rotationYZ * f4 - rotationXZ * f4)).tex(f1, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
/* 184 */     worldRendererIn.pos((f5 - rotationX * f4 + rotationXY * f4), (f6 + rotationZ * f4), (f7 - rotationYZ * f4 + rotationXZ * f4)).tex(f1, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
/* 185 */     worldRendererIn.pos((f5 + rotationX * f4 + rotationXY * f4), (f6 + rotationZ * f4), (f7 + rotationYZ * f4 + rotationXZ * f4)).tex(f, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
/* 186 */     worldRendererIn.pos((f5 + rotationX * f4 - rotationXY * f4), (f6 - rotationZ * f4), (f7 + rotationYZ * f4 - rotationXZ * f4)).tex(f, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
/*     */   }
/*     */   
/*     */   public int getFXLayer() {
/* 190 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParticleIcon(TextureAtlasSprite icon) {
/* 209 */     int i = getFXLayer();
/*     */     
/* 211 */     if (i == 1) {
/* 212 */       this.particleIcon = icon;
/*     */     } else {
/* 214 */       throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParticleTextureIndex(int particleTextureIndex) {
/* 222 */     if (getFXLayer() != 0) {
/* 223 */       throw new RuntimeException("Invalid call to Particle.setMiscTex");
/*     */     }
/* 225 */     this.particleTextureIndexX = particleTextureIndex % 16;
/* 226 */     this.particleTextureIndexY = particleTextureIndex / 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextTextureIndexX() {
/* 231 */     this.particleTextureIndexX++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 238 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 242 */     return String.valueOf(getClass().getSimpleName()) + ", Pos (" + this.posX + "," + this.posY + "," + this.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\particle\EntityFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */