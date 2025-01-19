package net.minecraft.entity;

import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IEntityMultiPart {
  World getWorld();
  
  boolean attackEntityFromPart(EntityDragonPart paramEntityDragonPart, DamageSource paramDamageSource, float paramFloat);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\entity\IEntityMultiPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */