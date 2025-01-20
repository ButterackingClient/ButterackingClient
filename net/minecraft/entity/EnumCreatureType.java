/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.monster.IMob;
/*    */ import net.minecraft.entity.passive.EntityAmbientCreature;
/*    */ import net.minecraft.entity.passive.EntityAnimal;
/*    */ import net.minecraft.entity.passive.EntityWaterMob;
/*    */ import net.minecraft.entity.passive.IAnimals;
/*    */ 
/*    */ public enum EnumCreatureType {
/* 11 */   MONSTER((Class)IMob.class, 70, Material.air, false, false),
/* 12 */   CREATURE((Class)EntityAnimal.class, 10, Material.air, true, true),
/* 13 */   AMBIENT((Class)EntityAmbientCreature.class, 15, Material.air, true, false),
/* 14 */   WATER_CREATURE((Class)EntityWaterMob.class, 5, Material.water, true, false);
/*    */ 
/*    */   
/*    */   private final Class<? extends IAnimals> creatureClass;
/*    */ 
/*    */   
/*    */   private final int maxNumberOfCreature;
/*    */ 
/*    */   
/*    */   private final Material creatureMaterial;
/*    */   
/*    */   private final boolean isPeacefulCreature;
/*    */   
/*    */   private final boolean isAnimal;
/*    */ 
/*    */   
/*    */   EnumCreatureType(Class<? extends IAnimals> creatureClassIn, int maxNumberOfCreatureIn, Material creatureMaterialIn, boolean isPeacefulCreatureIn, boolean isAnimalIn) {
/* 31 */     this.creatureClass = creatureClassIn;
/* 32 */     this.maxNumberOfCreature = maxNumberOfCreatureIn;
/* 33 */     this.creatureMaterial = creatureMaterialIn;
/* 34 */     this.isPeacefulCreature = isPeacefulCreatureIn;
/* 35 */     this.isAnimal = isAnimalIn;
/*    */   }
/*    */   
/*    */   public Class<? extends IAnimals> getCreatureClass() {
/* 39 */     return this.creatureClass;
/*    */   }
/*    */   
/*    */   public int getMaxNumberOfCreature() {
/* 43 */     return this.maxNumberOfCreature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getPeacefulCreature() {
/* 50 */     return this.isPeacefulCreature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getAnimal() {
/* 57 */     return this.isAnimal;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\entity\EnumCreatureType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */