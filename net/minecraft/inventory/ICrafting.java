package net.minecraft.inventory;

import java.util.List;
import net.minecraft.item.ItemStack;

public interface ICrafting {
  void updateCraftingInventory(Container paramContainer, List<ItemStack> paramList);
  
  void sendSlotContents(Container paramContainer, int paramInt, ItemStack paramItemStack);
  
  void sendProgressBarUpdate(Container paramContainer, int paramInt1, int paramInt2);
  
  void sendAllWindowProperties(Container paramContainer, IInventory paramIInventory);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\inventory\ICrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */