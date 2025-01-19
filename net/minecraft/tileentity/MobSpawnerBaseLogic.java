/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MobSpawnerBaseLogic
/*     */ {
/*  27 */   private int spawnDelay = 20;
/*  28 */   private String mobID = "Pig";
/*  29 */   private final List<WeightedRandomMinecart> minecartToSpawn = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   private WeightedRandomMinecart randomEntity;
/*     */ 
/*     */   
/*     */   private double mobRotation;
/*     */ 
/*     */   
/*     */   private double prevMobRotation;
/*     */ 
/*     */   
/*  41 */   private int minSpawnDelay = 200;
/*  42 */   private int maxSpawnDelay = 800;
/*  43 */   private int spawnCount = 4;
/*     */ 
/*     */   
/*     */   private Entity cachedEntity;
/*     */ 
/*     */   
/*  49 */   private int maxNearbyEntities = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private int activatingRangeFromPlayer = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private int spawnRange = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getEntityNameToSpawn() {
/*  65 */     if (getRandomEntity() == null) {
/*  66 */       if (this.mobID != null && this.mobID.equals("Minecart")) {
/*  67 */         this.mobID = "MinecartRideable";
/*     */       }
/*     */       
/*  70 */       return this.mobID;
/*     */     } 
/*  72 */     return (getRandomEntity()).entityType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityName(String name) {
/*  77 */     this.mobID = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isActivated() {
/*  84 */     BlockPos blockpos = getSpawnerPosition();
/*  85 */     return getSpawnerWorld().isAnyPlayerWithinRangeAt(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, this.activatingRangeFromPlayer);
/*     */   }
/*     */   
/*     */   public void updateSpawner() {
/*  89 */     if (isActivated()) {
/*  90 */       BlockPos blockpos = getSpawnerPosition();
/*     */       
/*  92 */       if ((getSpawnerWorld()).isRemote) {
/*  93 */         double d3 = (blockpos.getX() + (getSpawnerWorld()).rand.nextFloat());
/*  94 */         double d4 = (blockpos.getY() + (getSpawnerWorld()).rand.nextFloat());
/*  95 */         double d5 = (blockpos.getZ() + (getSpawnerWorld()).rand.nextFloat());
/*  96 */         getSpawnerWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
/*  97 */         getSpawnerWorld().spawnParticle(EnumParticleTypes.FLAME, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         
/*  99 */         if (this.spawnDelay > 0) {
/* 100 */           this.spawnDelay--;
/*     */         }
/*     */         
/* 103 */         this.prevMobRotation = this.mobRotation;
/* 104 */         this.mobRotation = (this.mobRotation + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
/*     */       } else {
/* 106 */         if (this.spawnDelay == -1) {
/* 107 */           resetTimer();
/*     */         }
/*     */         
/* 110 */         if (this.spawnDelay > 0) {
/* 111 */           this.spawnDelay--;
/*     */           
/*     */           return;
/*     */         } 
/* 115 */         boolean flag = false;
/*     */         
/* 117 */         for (int i = 0; i < this.spawnCount; i++) {
/* 118 */           Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), getSpawnerWorld());
/*     */           
/* 120 */           if (entity == null) {
/*     */             return;
/*     */           }
/*     */           
/* 124 */           int j = getSpawnerWorld().getEntitiesWithinAABB(entity.getClass(), (new AxisAlignedBB(blockpos.getX(), blockpos.getY(), blockpos.getZ(), (blockpos.getX() + 1), (blockpos.getY() + 1), (blockpos.getZ() + 1))).expand(this.spawnRange, this.spawnRange, this.spawnRange)).size();
/*     */           
/* 126 */           if (j >= this.maxNearbyEntities) {
/* 127 */             resetTimer();
/*     */             
/*     */             return;
/*     */           } 
/* 131 */           double d0 = blockpos.getX() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange + 0.5D;
/* 132 */           double d1 = (blockpos.getY() + (getSpawnerWorld()).rand.nextInt(3) - 1);
/* 133 */           double d2 = blockpos.getZ() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange + 0.5D;
/* 134 */           EntityLiving entityliving = (entity instanceof EntityLiving) ? (EntityLiving)entity : null;
/* 135 */           entity.setLocationAndAngles(d0, d1, d2, (getSpawnerWorld()).rand.nextFloat() * 360.0F, 0.0F);
/*     */           
/* 137 */           if (entityliving == null || (entityliving.getCanSpawnHere() && entityliving.isNotColliding())) {
/* 138 */             spawnNewEntity(entity, true);
/* 139 */             getSpawnerWorld().playAuxSFX(2004, blockpos, 0);
/*     */             
/* 141 */             if (entityliving != null) {
/* 142 */               entityliving.spawnExplosionParticle();
/*     */             }
/*     */             
/* 145 */             flag = true;
/*     */           } 
/*     */         } 
/*     */         
/* 149 */         if (flag) {
/* 150 */           resetTimer();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Entity spawnNewEntity(Entity entityIn, boolean spawn) {
/* 157 */     if (getRandomEntity() != null) {
/* 158 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 159 */       entityIn.writeToNBTOptional(nbttagcompound);
/*     */       
/* 161 */       for (String s : (getRandomEntity()).nbtData.getKeySet()) {
/* 162 */         NBTBase nbtbase = (getRandomEntity()).nbtData.getTag(s);
/* 163 */         nbttagcompound.setTag(s, nbtbase.copy());
/*     */       } 
/*     */       
/* 166 */       entityIn.readFromNBT(nbttagcompound);
/*     */       
/* 168 */       if (entityIn.worldObj != null && spawn) {
/* 169 */         entityIn.worldObj.spawnEntityInWorld(entityIn);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 174 */       for (Entity entity = entityIn; nbttagcompound.hasKey("Riding", 10); nbttagcompound = nbttagcompound2) {
/* 175 */         NBTTagCompound nbttagcompound2 = nbttagcompound.getCompoundTag("Riding");
/* 176 */         Entity entity1 = EntityList.createEntityByName(nbttagcompound2.getString("id"), entityIn.worldObj);
/*     */         
/* 178 */         if (entity1 != null) {
/* 179 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 180 */           entity1.writeToNBTOptional(nbttagcompound1);
/*     */           
/* 182 */           for (String s1 : nbttagcompound2.getKeySet()) {
/* 183 */             NBTBase nbtbase1 = nbttagcompound2.getTag(s1);
/* 184 */             nbttagcompound1.setTag(s1, nbtbase1.copy());
/*     */           } 
/*     */           
/* 187 */           entity1.readFromNBT(nbttagcompound1);
/* 188 */           entity1.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
/*     */           
/* 190 */           if (entityIn.worldObj != null && spawn) {
/* 191 */             entityIn.worldObj.spawnEntityInWorld(entity1);
/*     */           }
/*     */           
/* 194 */           entity.mountEntity(entity1);
/*     */         } 
/*     */         
/* 197 */         entity = entity1;
/*     */       } 
/* 199 */     } else if (entityIn instanceof net.minecraft.entity.EntityLivingBase && entityIn.worldObj != null && spawn) {
/* 200 */       if (entityIn instanceof EntityLiving) {
/* 201 */         ((EntityLiving)entityIn).onInitialSpawn(entityIn.worldObj.getDifficultyForLocation(new BlockPos(entityIn)), null);
/*     */       }
/*     */       
/* 204 */       entityIn.worldObj.spawnEntityInWorld(entityIn);
/*     */     } 
/*     */     
/* 207 */     return entityIn;
/*     */   }
/*     */   
/*     */   private void resetTimer() {
/* 211 */     if (this.maxSpawnDelay <= this.minSpawnDelay) {
/* 212 */       this.spawnDelay = this.minSpawnDelay;
/*     */     } else {
/* 214 */       int i = this.maxSpawnDelay - this.minSpawnDelay;
/* 215 */       this.spawnDelay = this.minSpawnDelay + (getSpawnerWorld()).rand.nextInt(i);
/*     */     } 
/*     */     
/* 218 */     if (this.minecartToSpawn.size() > 0) {
/* 219 */       setRandomEntity((WeightedRandomMinecart)WeightedRandom.getRandomItem((getSpawnerWorld()).rand, this.minecartToSpawn));
/*     */     }
/*     */     
/* 222 */     func_98267_a(1);
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/* 226 */     this.mobID = nbt.getString("EntityId");
/* 227 */     this.spawnDelay = nbt.getShort("Delay");
/* 228 */     this.minecartToSpawn.clear();
/*     */     
/* 230 */     if (nbt.hasKey("SpawnPotentials", 9)) {
/* 231 */       NBTTagList nbttaglist = nbt.getTagList("SpawnPotentials", 10);
/*     */       
/* 233 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 234 */         this.minecartToSpawn.add(new WeightedRandomMinecart(nbttaglist.getCompoundTagAt(i)));
/*     */       }
/*     */     } 
/*     */     
/* 238 */     if (nbt.hasKey("SpawnData", 10)) {
/* 239 */       setRandomEntity(new WeightedRandomMinecart(nbt.getCompoundTag("SpawnData"), this.mobID));
/*     */     } else {
/* 241 */       setRandomEntity(null);
/*     */     } 
/*     */     
/* 244 */     if (nbt.hasKey("MinSpawnDelay", 99)) {
/* 245 */       this.minSpawnDelay = nbt.getShort("MinSpawnDelay");
/* 246 */       this.maxSpawnDelay = nbt.getShort("MaxSpawnDelay");
/* 247 */       this.spawnCount = nbt.getShort("SpawnCount");
/*     */     } 
/*     */     
/* 250 */     if (nbt.hasKey("MaxNearbyEntities", 99)) {
/* 251 */       this.maxNearbyEntities = nbt.getShort("MaxNearbyEntities");
/* 252 */       this.activatingRangeFromPlayer = nbt.getShort("RequiredPlayerRange");
/*     */     } 
/*     */     
/* 255 */     if (nbt.hasKey("SpawnRange", 99)) {
/* 256 */       this.spawnRange = nbt.getShort("SpawnRange");
/*     */     }
/*     */     
/* 259 */     if (getSpawnerWorld() != null) {
/* 260 */       this.cachedEntity = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 265 */     String s = getEntityNameToSpawn();
/*     */     
/* 267 */     if (!StringUtils.isNullOrEmpty(s)) {
/* 268 */       nbt.setString("EntityId", s);
/* 269 */       nbt.setShort("Delay", (short)this.spawnDelay);
/* 270 */       nbt.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
/* 271 */       nbt.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
/* 272 */       nbt.setShort("SpawnCount", (short)this.spawnCount);
/* 273 */       nbt.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
/* 274 */       nbt.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
/* 275 */       nbt.setShort("SpawnRange", (short)this.spawnRange);
/*     */       
/* 277 */       if (getRandomEntity() != null) {
/* 278 */         nbt.setTag("SpawnData", (getRandomEntity()).nbtData.copy());
/*     */       }
/*     */       
/* 281 */       if (getRandomEntity() != null || this.minecartToSpawn.size() > 0) {
/* 282 */         NBTTagList nbttaglist = new NBTTagList();
/*     */         
/* 284 */         if (this.minecartToSpawn.size() > 0) {
/* 285 */           for (WeightedRandomMinecart mobspawnerbaselogic$weightedrandomminecart : this.minecartToSpawn) {
/* 286 */             nbttaglist.appendTag((NBTBase)mobspawnerbaselogic$weightedrandomminecart.toNBT());
/*     */           }
/*     */         } else {
/* 289 */           nbttaglist.appendTag((NBTBase)getRandomEntity().toNBT());
/*     */         } 
/*     */         
/* 292 */         nbt.setTag("SpawnPotentials", (NBTBase)nbttaglist);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Entity func_180612_a(World worldIn) {
/* 298 */     if (this.cachedEntity == null) {
/* 299 */       Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), worldIn);
/*     */       
/* 301 */       if (entity != null) {
/* 302 */         entity = spawnNewEntity(entity, false);
/* 303 */         this.cachedEntity = entity;
/*     */       } 
/*     */     } 
/*     */     
/* 307 */     return this.cachedEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDelayToMin(int delay) {
/* 314 */     if (delay == 1 && (getSpawnerWorld()).isRemote) {
/* 315 */       this.spawnDelay = this.minSpawnDelay;
/* 316 */       return true;
/*     */     } 
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private WeightedRandomMinecart getRandomEntity() {
/* 323 */     return this.randomEntity;
/*     */   }
/*     */   
/*     */   public void setRandomEntity(WeightedRandomMinecart p_98277_1_) {
/* 327 */     this.randomEntity = p_98277_1_;
/*     */   }
/*     */   
/*     */   public abstract void func_98267_a(int paramInt);
/*     */   
/*     */   public abstract World getSpawnerWorld();
/*     */   
/*     */   public abstract BlockPos getSpawnerPosition();
/*     */   
/*     */   public double getMobRotation() {
/* 337 */     return this.mobRotation;
/*     */   }
/*     */   
/*     */   public double getPrevMobRotation() {
/* 341 */     return this.prevMobRotation;
/*     */   }
/*     */   
/*     */   public class WeightedRandomMinecart extends WeightedRandom.Item {
/*     */     private final NBTTagCompound nbtData;
/*     */     private final String entityType;
/*     */     
/*     */     public WeightedRandomMinecart(NBTTagCompound tagCompound) {
/* 349 */       this(tagCompound.getCompoundTag("Properties"), tagCompound.getString("Type"), tagCompound.getInteger("Weight"));
/*     */     }
/*     */     
/*     */     public WeightedRandomMinecart(NBTTagCompound tagCompound, String type) {
/* 353 */       this(tagCompound, type, 1);
/*     */     }
/*     */     
/*     */     private WeightedRandomMinecart(NBTTagCompound tagCompound, String type, int weight) {
/* 357 */       super(weight);
/*     */       
/* 359 */       if (type.equals("Minecart")) {
/* 360 */         if (tagCompound != null) {
/* 361 */           type = EntityMinecart.EnumMinecartType.byNetworkID(tagCompound.getInteger("Type")).getName();
/*     */         } else {
/* 363 */           type = "MinecartRideable";
/*     */         } 
/*     */       }
/*     */       
/* 367 */       this.nbtData = tagCompound;
/* 368 */       this.entityType = type;
/*     */     }
/*     */     
/*     */     public NBTTagCompound toNBT() {
/* 372 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 373 */       nbttagcompound.setTag("Properties", (NBTBase)this.nbtData);
/* 374 */       nbttagcompound.setString("Type", this.entityType);
/* 375 */       nbttagcompound.setInteger("Weight", this.itemWeight);
/* 376 */       return nbttagcompound;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\tileentity\MobSpawnerBaseLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */