/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesFood
/*    */ {
/*    */   public void addRecipes(CraftingManager p_77608_1_) {
/* 13 */     p_77608_1_.addShapelessRecipe(new ItemStack(Items.mushroom_stew), new Object[] { Blocks.brown_mushroom, Blocks.red_mushroom, Items.bowl });
/* 14 */     p_77608_1_.addRecipe(new ItemStack(Items.cookie, 8), new Object[] { "#X#", Character.valueOf('X'), new ItemStack(Items.dye, 1, EnumDyeColor.BROWN.getDyeDamage()), Character.valueOf('#'), Items.wheat });
/* 15 */     p_77608_1_.addRecipe(new ItemStack(Items.rabbit_stew), new Object[] { " R ", "CPM", " B ", Character.valueOf('R'), new ItemStack(Items.cooked_rabbit), Character.valueOf('C'), Items.carrot, Character.valueOf('P'), Items.baked_potato, Character.valueOf('M'), Blocks.brown_mushroom, Character.valueOf('B'), Items.bowl });
/* 16 */     p_77608_1_.addRecipe(new ItemStack(Items.rabbit_stew), new Object[] { " R ", "CPD", " B ", Character.valueOf('R'), new ItemStack(Items.cooked_rabbit), Character.valueOf('C'), Items.carrot, Character.valueOf('P'), Items.baked_potato, Character.valueOf('D'), Blocks.red_mushroom, Character.valueOf('B'), Items.bowl });
/* 17 */     p_77608_1_.addRecipe(new ItemStack(Blocks.melon_block), new Object[] { "MMM", "MMM", "MMM", Character.valueOf('M'), Items.melon });
/* 18 */     p_77608_1_.addRecipe(new ItemStack(Items.melon_seeds), new Object[] { "M", Character.valueOf('M'), Items.melon });
/* 19 */     p_77608_1_.addRecipe(new ItemStack(Items.pumpkin_seeds, 4), new Object[] { "M", Character.valueOf('M'), Blocks.pumpkin });
/* 20 */     p_77608_1_.addShapelessRecipe(new ItemStack(Items.pumpkin_pie), new Object[] { Blocks.pumpkin, Items.sugar, Items.egg });
/* 21 */     p_77608_1_.addShapelessRecipe(new ItemStack(Items.fermented_spider_eye), new Object[] { Items.spider_eye, Blocks.brown_mushroom, Items.sugar });
/* 22 */     p_77608_1_.addShapelessRecipe(new ItemStack(Items.blaze_powder, 2), new Object[] { Items.blaze_rod });
/* 23 */     p_77608_1_.addShapelessRecipe(new ItemStack(Items.magma_cream), new Object[] { Items.blaze_powder, Items.slime_ball });
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\item\crafting\RecipesFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */