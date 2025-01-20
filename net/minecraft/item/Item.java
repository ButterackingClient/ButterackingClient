/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.BlockPrismarine;
/*     */ import net.minecraft.block.BlockRedSandstone;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.BlockSandStone;
/*     */ import net.minecraft.block.BlockSilverfish;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.BlockStoneBrick;
/*     */ import net.minecraft.block.BlockWall;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.item.EntityPainting;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.util.Vec3;
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
/*     */ public class Item
/*     */ {
/*     */   public Item() {
/*  63 */     this.maxStackSize = 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final RegistryNamespaced<ResourceLocation, Item> itemRegistry = new RegistryNamespaced();
/*     */   
/*     */   private static final Map<Block, Item> BLOCK_TO_ITEM = Maps.newHashMap();
/*     */   
/*     */   protected static final UUID itemModifierUUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*     */   
/*     */   private CreativeTabs tabToDisplayOn;
/*     */   
/*     */   protected static Random itemRand = new Random();
/*     */   
/*     */   protected int maxStackSize;
/*     */   
/*     */   private int maxDamage;
/*     */   
/*     */   protected boolean bFull3D;
/*     */   
/*     */   protected boolean hasSubtypes;
/*     */   
/*     */   private Item containerItem;
/*     */   
/*     */   private String potionEffect;
/*     */   
/*     */   private String unlocalizedName;
/*     */   
/*     */   public static int getIdFromItem(Item itemIn) {
/*  92 */     return (itemIn == null) ? 0 : itemRegistry.getIDForObject(itemIn);
/*     */   }
/*     */   
/*     */   public static Item getItemById(int id) {
/*  96 */     return (Item)itemRegistry.getObjectById(id);
/*     */   }
/*     */   
/*     */   public static Item getItemFromBlock(Block blockIn) {
/* 100 */     return BLOCK_TO_ITEM.get(blockIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Item getByNameOrId(String id) {
/* 108 */     Item item = (Item)itemRegistry.getObject(new ResourceLocation(id));
/*     */     
/* 110 */     if (item == null) {
/*     */       try {
/* 112 */         return getItemById(Integer.parseInt(id));
/* 113 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateItemStackNBT(NBTTagCompound nbt) {
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public Item setMaxStackSize(int maxStackSize) {
/* 129 */     this.maxStackSize = maxStackSize;
/* 130 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public float getStrVsBlock(ItemStack stack, Block state) {
/* 141 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 148 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/* 156 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit() {
/* 163 */     return this.maxStackSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata(int damage) {
/* 171 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean getHasSubtypes() {
/* 175 */     return this.hasSubtypes;
/*     */   }
/*     */   
/*     */   protected Item setHasSubtypes(boolean hasSubtypes) {
/* 179 */     this.hasSubtypes = hasSubtypes;
/* 180 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage() {
/* 187 */     return this.maxDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item setMaxDamage(int maxDamageIn) {
/* 194 */     this.maxDamage = maxDamageIn;
/* 195 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isDamageable() {
/* 199 */     return (this.maxDamage > 0 && !this.hasSubtypes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
/* 214 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHarvestBlock(Block blockIn) {
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item setFull3D() {
/* 235 */     this.bFull3D = true;
/* 236 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 243 */     return this.bFull3D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRotateAroundWhenRendering() {
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item setUnlocalizedName(String unlocalizedName) {
/* 258 */     this.unlocalizedName = unlocalizedName;
/* 259 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedNameInefficiently(ItemStack stack) {
/* 267 */     String s = getUnlocalizedName(stack);
/* 268 */     return (s == null) ? "" : StatCollector.translateToLocal(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/* 275 */     return "item." + this.unlocalizedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/* 283 */     return "item." + this.unlocalizedName;
/*     */   }
/*     */   
/*     */   public Item setContainerItem(Item containerItem) {
/* 287 */     this.containerItem = containerItem;
/* 288 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getShareTag() {
/* 295 */     return true;
/*     */   }
/*     */   
/*     */   public Item getContainerItem() {
/* 299 */     return this.containerItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasContainerItem() {
/* 306 */     return (this.containerItem != null);
/*     */   }
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 310 */     return 16777215;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMap() {
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack stack) {
/* 337 */     return EnumAction.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack stack) {
/* 344 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item setPotionEffect(String potionEffect) {
/* 357 */     this.potionEffect = potionEffect;
/* 358 */     return this;
/*     */   }
/*     */   
/*     */   public String getPotionEffect(ItemStack stack) {
/* 362 */     return this.potionEffect;
/*     */   }
/*     */   
/*     */   public boolean isPotionIngredient(ItemStack stack) {
/* 366 */     return (getPotionEffect(stack) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/* 376 */     return StatCollector.translateToLocal(String.valueOf(getUnlocalizedNameInefficiently(stack)) + ".name").trim();
/*     */   }
/*     */   
/*     */   public boolean hasEffect(ItemStack stack) {
/* 380 */     return stack.isItemEnchanted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack stack) {
/* 387 */     return stack.isItemEnchanted() ? EnumRarity.RARE : EnumRarity.COMMON;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemTool(ItemStack stack) {
/* 394 */     return (getItemStackLimit() == 1 && isDamageable());
/*     */   }
/*     */   
/*     */   protected MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
/* 398 */     float f = playerIn.rotationPitch;
/* 399 */     float f1 = playerIn.rotationYaw;
/* 400 */     double d0 = playerIn.posX;
/* 401 */     double d1 = playerIn.posY + playerIn.getEyeHeight();
/* 402 */     double d2 = playerIn.posZ;
/* 403 */     Vec3 vec3 = new Vec3(d0, d1, d2);
/* 404 */     float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
/* 405 */     float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
/* 406 */     float f4 = -MathHelper.cos(-f * 0.017453292F);
/* 407 */     float f5 = MathHelper.sin(-f * 0.017453292F);
/* 408 */     float f6 = f3 * f4;
/* 409 */     float f7 = f2 * f4;
/* 410 */     double d3 = 5.0D;
/* 411 */     Vec3 vec31 = vec3.addVector(f6 * d3, f5 * d3, f7 * d3);
/* 412 */     return worldIn.rayTraceBlocks(vec3, vec31, useLiquids, !useLiquids, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 419 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 426 */     subItems.add(new ItemStack(itemIn, 1, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeTabs getCreativeTab() {
/* 433 */     return this.tabToDisplayOn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item setCreativeTab(CreativeTabs tab) {
/* 440 */     this.tabToDisplayOn = tab;
/* 441 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canItemEditBlocks() {
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/* 456 */     return false;
/*     */   }
/*     */   
/*     */   public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
/* 460 */     return (Multimap<String, AttributeModifier>)HashMultimap.create();
/*     */   }
/*     */   
/*     */   public static void registerItems() {
/* 464 */     registerItemBlock(Blocks.stone, (new ItemMultiTexture(Blocks.stone, Blocks.stone, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 466 */               return BlockStone.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 468 */           })).setUnlocalizedName("stone"));
/* 469 */     registerItemBlock((Block)Blocks.grass, new ItemColored((Block)Blocks.grass, false));
/* 470 */     registerItemBlock(Blocks.dirt, (new ItemMultiTexture(Blocks.dirt, Blocks.dirt, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 472 */               return BlockDirt.DirtType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 474 */           })).setUnlocalizedName("dirt"));
/* 475 */     registerItemBlock(Blocks.cobblestone);
/* 476 */     registerItemBlock(Blocks.planks, (new ItemMultiTexture(Blocks.planks, Blocks.planks, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 478 */               return BlockPlanks.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 480 */           })).setUnlocalizedName("wood"));
/* 481 */     registerItemBlock(Blocks.sapling, (new ItemMultiTexture(Blocks.sapling, Blocks.sapling, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 483 */               return BlockPlanks.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 485 */           })).setUnlocalizedName("sapling"));
/* 486 */     registerItemBlock(Blocks.bedrock);
/* 487 */     registerItemBlock((Block)Blocks.sand, (new ItemMultiTexture((Block)Blocks.sand, (Block)Blocks.sand, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 489 */               return BlockSand.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 491 */           })).setUnlocalizedName("sand"));
/* 492 */     registerItemBlock(Blocks.gravel);
/* 493 */     registerItemBlock(Blocks.gold_ore);
/* 494 */     registerItemBlock(Blocks.iron_ore);
/* 495 */     registerItemBlock(Blocks.coal_ore);
/* 496 */     registerItemBlock(Blocks.log, (new ItemMultiTexture(Blocks.log, Blocks.log, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 498 */               return BlockPlanks.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 500 */           })).setUnlocalizedName("log"));
/* 501 */     registerItemBlock(Blocks.log2, (new ItemMultiTexture(Blocks.log2, Blocks.log2, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 503 */               return BlockPlanks.EnumType.byMetadata(p_apply_1_.getMetadata() + 4).getUnlocalizedName();
/*     */             }
/* 505 */           })).setUnlocalizedName("log"));
/* 506 */     registerItemBlock((Block)Blocks.leaves, (new ItemLeaves(Blocks.leaves)).setUnlocalizedName("leaves"));
/* 507 */     registerItemBlock((Block)Blocks.leaves2, (new ItemLeaves(Blocks.leaves2)).setUnlocalizedName("leaves"));
/* 508 */     registerItemBlock(Blocks.sponge, (new ItemMultiTexture(Blocks.sponge, Blocks.sponge, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 510 */               return ((p_apply_1_.getMetadata() & 0x1) == 1) ? "wet" : "dry";
/*     */             }
/* 512 */           })).setUnlocalizedName("sponge"));
/* 513 */     registerItemBlock(Blocks.glass);
/* 514 */     registerItemBlock(Blocks.lapis_ore);
/* 515 */     registerItemBlock(Blocks.lapis_block);
/* 516 */     registerItemBlock(Blocks.dispenser);
/* 517 */     registerItemBlock(Blocks.sandstone, (new ItemMultiTexture(Blocks.sandstone, Blocks.sandstone, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 519 */               return BlockSandStone.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 521 */           })).setUnlocalizedName("sandStone"));
/* 522 */     registerItemBlock(Blocks.noteblock);
/* 523 */     registerItemBlock(Blocks.golden_rail);
/* 524 */     registerItemBlock(Blocks.detector_rail);
/* 525 */     registerItemBlock((Block)Blocks.sticky_piston, new ItemPiston((Block)Blocks.sticky_piston));
/* 526 */     registerItemBlock(Blocks.web);
/* 527 */     registerItemBlock((Block)Blocks.tallgrass, (new ItemColored((Block)Blocks.tallgrass, true)).setSubtypeNames(new String[] { "shrub", "grass", "fern" }));
/* 528 */     registerItemBlock((Block)Blocks.deadbush);
/* 529 */     registerItemBlock((Block)Blocks.piston, new ItemPiston((Block)Blocks.piston));
/* 530 */     registerItemBlock(Blocks.wool, (new ItemCloth(Blocks.wool)).setUnlocalizedName("cloth"));
/* 531 */     registerItemBlock((Block)Blocks.yellow_flower, (new ItemMultiTexture((Block)Blocks.yellow_flower, (Block)Blocks.yellow_flower, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 533 */               return BlockFlower.EnumFlowerType.getType(BlockFlower.EnumFlowerColor.YELLOW, p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 535 */           })).setUnlocalizedName("flower"));
/* 536 */     registerItemBlock((Block)Blocks.red_flower, (new ItemMultiTexture((Block)Blocks.red_flower, (Block)Blocks.red_flower, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 538 */               return BlockFlower.EnumFlowerType.getType(BlockFlower.EnumFlowerColor.RED, p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 540 */           })).setUnlocalizedName("rose"));
/* 541 */     registerItemBlock((Block)Blocks.brown_mushroom);
/* 542 */     registerItemBlock((Block)Blocks.red_mushroom);
/* 543 */     registerItemBlock(Blocks.gold_block);
/* 544 */     registerItemBlock(Blocks.iron_block);
/* 545 */     registerItemBlock((Block)Blocks.stone_slab, (new ItemSlab((Block)Blocks.stone_slab, Blocks.stone_slab, Blocks.double_stone_slab)).setUnlocalizedName("stoneSlab"));
/* 546 */     registerItemBlock(Blocks.brick_block);
/* 547 */     registerItemBlock(Blocks.tnt);
/* 548 */     registerItemBlock(Blocks.bookshelf);
/* 549 */     registerItemBlock(Blocks.mossy_cobblestone);
/* 550 */     registerItemBlock(Blocks.obsidian);
/* 551 */     registerItemBlock(Blocks.torch);
/* 552 */     registerItemBlock(Blocks.mob_spawner);
/* 553 */     registerItemBlock(Blocks.oak_stairs);
/* 554 */     registerItemBlock((Block)Blocks.chest);
/* 555 */     registerItemBlock(Blocks.diamond_ore);
/* 556 */     registerItemBlock(Blocks.diamond_block);
/* 557 */     registerItemBlock(Blocks.crafting_table);
/* 558 */     registerItemBlock(Blocks.farmland);
/* 559 */     registerItemBlock(Blocks.furnace);
/* 560 */     registerItemBlock(Blocks.lit_furnace);
/* 561 */     registerItemBlock(Blocks.ladder);
/* 562 */     registerItemBlock(Blocks.rail);
/* 563 */     registerItemBlock(Blocks.stone_stairs);
/* 564 */     registerItemBlock(Blocks.lever);
/* 565 */     registerItemBlock(Blocks.stone_pressure_plate);
/* 566 */     registerItemBlock(Blocks.wooden_pressure_plate);
/* 567 */     registerItemBlock(Blocks.redstone_ore);
/* 568 */     registerItemBlock(Blocks.redstone_torch);
/* 569 */     registerItemBlock(Blocks.stone_button);
/* 570 */     registerItemBlock(Blocks.snow_layer, new ItemSnow(Blocks.snow_layer));
/* 571 */     registerItemBlock(Blocks.ice);
/* 572 */     registerItemBlock(Blocks.snow);
/* 573 */     registerItemBlock((Block)Blocks.cactus);
/* 574 */     registerItemBlock(Blocks.clay);
/* 575 */     registerItemBlock(Blocks.jukebox);
/* 576 */     registerItemBlock(Blocks.oak_fence);
/* 577 */     registerItemBlock(Blocks.spruce_fence);
/* 578 */     registerItemBlock(Blocks.birch_fence);
/* 579 */     registerItemBlock(Blocks.jungle_fence);
/* 580 */     registerItemBlock(Blocks.dark_oak_fence);
/* 581 */     registerItemBlock(Blocks.acacia_fence);
/* 582 */     registerItemBlock(Blocks.pumpkin);
/* 583 */     registerItemBlock(Blocks.netherrack);
/* 584 */     registerItemBlock(Blocks.soul_sand);
/* 585 */     registerItemBlock(Blocks.glowstone);
/* 586 */     registerItemBlock(Blocks.lit_pumpkin);
/* 587 */     registerItemBlock(Blocks.trapdoor);
/* 588 */     registerItemBlock(Blocks.monster_egg, (new ItemMultiTexture(Blocks.monster_egg, Blocks.monster_egg, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 590 */               return BlockSilverfish.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 592 */           })).setUnlocalizedName("monsterStoneEgg"));
/* 593 */     registerItemBlock(Blocks.stonebrick, (new ItemMultiTexture(Blocks.stonebrick, Blocks.stonebrick, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 595 */               return BlockStoneBrick.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 597 */           })).setUnlocalizedName("stonebricksmooth"));
/* 598 */     registerItemBlock(Blocks.brown_mushroom_block);
/* 599 */     registerItemBlock(Blocks.red_mushroom_block);
/* 600 */     registerItemBlock(Blocks.iron_bars);
/* 601 */     registerItemBlock(Blocks.glass_pane);
/* 602 */     registerItemBlock(Blocks.melon_block);
/* 603 */     registerItemBlock(Blocks.vine, new ItemColored(Blocks.vine, false));
/* 604 */     registerItemBlock(Blocks.oak_fence_gate);
/* 605 */     registerItemBlock(Blocks.spruce_fence_gate);
/* 606 */     registerItemBlock(Blocks.birch_fence_gate);
/* 607 */     registerItemBlock(Blocks.jungle_fence_gate);
/* 608 */     registerItemBlock(Blocks.dark_oak_fence_gate);
/* 609 */     registerItemBlock(Blocks.acacia_fence_gate);
/* 610 */     registerItemBlock(Blocks.brick_stairs);
/* 611 */     registerItemBlock(Blocks.stone_brick_stairs);
/* 612 */     registerItemBlock((Block)Blocks.mycelium);
/* 613 */     registerItemBlock(Blocks.waterlily, new ItemLilyPad(Blocks.waterlily));
/* 614 */     registerItemBlock(Blocks.nether_brick);
/* 615 */     registerItemBlock(Blocks.nether_brick_fence);
/* 616 */     registerItemBlock(Blocks.nether_brick_stairs);
/* 617 */     registerItemBlock(Blocks.enchanting_table);
/* 618 */     registerItemBlock(Blocks.end_portal_frame);
/* 619 */     registerItemBlock(Blocks.end_stone);
/* 620 */     registerItemBlock(Blocks.dragon_egg);
/* 621 */     registerItemBlock(Blocks.redstone_lamp);
/* 622 */     registerItemBlock((Block)Blocks.wooden_slab, (new ItemSlab((Block)Blocks.wooden_slab, Blocks.wooden_slab, Blocks.double_wooden_slab)).setUnlocalizedName("woodSlab"));
/* 623 */     registerItemBlock(Blocks.sandstone_stairs);
/* 624 */     registerItemBlock(Blocks.emerald_ore);
/* 625 */     registerItemBlock(Blocks.ender_chest);
/* 626 */     registerItemBlock((Block)Blocks.tripwire_hook);
/* 627 */     registerItemBlock(Blocks.emerald_block);
/* 628 */     registerItemBlock(Blocks.spruce_stairs);
/* 629 */     registerItemBlock(Blocks.birch_stairs);
/* 630 */     registerItemBlock(Blocks.jungle_stairs);
/* 631 */     registerItemBlock(Blocks.command_block);
/* 632 */     registerItemBlock((Block)Blocks.beacon);
/* 633 */     registerItemBlock(Blocks.cobblestone_wall, (new ItemMultiTexture(Blocks.cobblestone_wall, Blocks.cobblestone_wall, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 635 */               return BlockWall.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 637 */           })).setUnlocalizedName("cobbleWall"));
/* 638 */     registerItemBlock(Blocks.wooden_button);
/* 639 */     registerItemBlock(Blocks.anvil, (new ItemAnvilBlock(Blocks.anvil)).setUnlocalizedName("anvil"));
/* 640 */     registerItemBlock(Blocks.trapped_chest);
/* 641 */     registerItemBlock(Blocks.light_weighted_pressure_plate);
/* 642 */     registerItemBlock(Blocks.heavy_weighted_pressure_plate);
/* 643 */     registerItemBlock((Block)Blocks.daylight_detector);
/* 644 */     registerItemBlock(Blocks.redstone_block);
/* 645 */     registerItemBlock(Blocks.quartz_ore);
/* 646 */     registerItemBlock((Block)Blocks.hopper);
/* 647 */     registerItemBlock(Blocks.quartz_block, (new ItemMultiTexture(Blocks.quartz_block, Blocks.quartz_block, new String[] { "default", "chiseled", "lines" })).setUnlocalizedName("quartzBlock"));
/* 648 */     registerItemBlock(Blocks.quartz_stairs);
/* 649 */     registerItemBlock(Blocks.activator_rail);
/* 650 */     registerItemBlock(Blocks.dropper);
/* 651 */     registerItemBlock(Blocks.stained_hardened_clay, (new ItemCloth(Blocks.stained_hardened_clay)).setUnlocalizedName("clayHardenedStained"));
/* 652 */     registerItemBlock(Blocks.barrier);
/* 653 */     registerItemBlock(Blocks.iron_trapdoor);
/* 654 */     registerItemBlock(Blocks.hay_block);
/* 655 */     registerItemBlock(Blocks.carpet, (new ItemCloth(Blocks.carpet)).setUnlocalizedName("woolCarpet"));
/* 656 */     registerItemBlock(Blocks.hardened_clay);
/* 657 */     registerItemBlock(Blocks.coal_block);
/* 658 */     registerItemBlock(Blocks.packed_ice);
/* 659 */     registerItemBlock(Blocks.acacia_stairs);
/* 660 */     registerItemBlock(Blocks.dark_oak_stairs);
/* 661 */     registerItemBlock(Blocks.slime_block);
/* 662 */     registerItemBlock((Block)Blocks.double_plant, (new ItemDoublePlant((Block)Blocks.double_plant, (Block)Blocks.double_plant, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 664 */               return BlockDoublePlant.EnumPlantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 666 */           })).setUnlocalizedName("doublePlant"));
/* 667 */     registerItemBlock((Block)Blocks.stained_glass, (new ItemCloth((Block)Blocks.stained_glass)).setUnlocalizedName("stainedGlass"));
/* 668 */     registerItemBlock((Block)Blocks.stained_glass_pane, (new ItemCloth((Block)Blocks.stained_glass_pane)).setUnlocalizedName("stainedGlassPane"));
/* 669 */     registerItemBlock(Blocks.prismarine, (new ItemMultiTexture(Blocks.prismarine, Blocks.prismarine, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 671 */               return BlockPrismarine.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 673 */           })).setUnlocalizedName("prismarine"));
/* 674 */     registerItemBlock(Blocks.sea_lantern);
/* 675 */     registerItemBlock(Blocks.red_sandstone, (new ItemMultiTexture(Blocks.red_sandstone, Blocks.red_sandstone, new Function<ItemStack, String>() {
/*     */             public String apply(ItemStack p_apply_1_) {
/* 677 */               return BlockRedSandstone.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
/*     */             }
/* 679 */           })).setUnlocalizedName("redSandStone"));
/* 680 */     registerItemBlock(Blocks.red_sandstone_stairs);
/* 681 */     registerItemBlock((Block)Blocks.stone_slab2, (new ItemSlab((Block)Blocks.stone_slab2, Blocks.stone_slab2, Blocks.double_stone_slab2)).setUnlocalizedName("stoneSlab2"));
/* 682 */     registerItem(256, "iron_shovel", (new ItemSpade(ToolMaterial.IRON)).setUnlocalizedName("shovelIron"));
/* 683 */     registerItem(257, "iron_pickaxe", (new ItemPickaxe(ToolMaterial.IRON)).setUnlocalizedName("pickaxeIron"));
/* 684 */     registerItem(258, "iron_axe", (new ItemAxe(ToolMaterial.IRON)).setUnlocalizedName("hatchetIron"));
/* 685 */     registerItem(259, "flint_and_steel", (new ItemFlintAndSteel()).setUnlocalizedName("flintAndSteel"));
/* 686 */     registerItem(260, "apple", (new ItemFood(4, 0.3F, false)).setUnlocalizedName("apple"));
/* 687 */     registerItem(261, "bow", (new ItemBow()).setUnlocalizedName("bow"));
/* 688 */     registerItem(262, "arrow", (new Item()).setUnlocalizedName("arrow").setCreativeTab(CreativeTabs.tabCombat));
/* 689 */     registerItem(263, "coal", (new ItemCoal()).setUnlocalizedName("coal"));
/* 690 */     registerItem(264, "diamond", (new Item()).setUnlocalizedName("diamond").setCreativeTab(CreativeTabs.tabMaterials));
/* 691 */     registerItem(265, "iron_ingot", (new Item()).setUnlocalizedName("ingotIron").setCreativeTab(CreativeTabs.tabMaterials));
/* 692 */     registerItem(266, "gold_ingot", (new Item()).setUnlocalizedName("ingotGold").setCreativeTab(CreativeTabs.tabMaterials));
/* 693 */     registerItem(267, "iron_sword", (new ItemSword(ToolMaterial.IRON)).setUnlocalizedName("swordIron"));
/* 694 */     registerItem(268, "wooden_sword", (new ItemSword(ToolMaterial.WOOD)).setUnlocalizedName("swordWood"));
/* 695 */     registerItem(269, "wooden_shovel", (new ItemSpade(ToolMaterial.WOOD)).setUnlocalizedName("shovelWood"));
/* 696 */     registerItem(270, "wooden_pickaxe", (new ItemPickaxe(ToolMaterial.WOOD)).setUnlocalizedName("pickaxeWood"));
/* 697 */     registerItem(271, "wooden_axe", (new ItemAxe(ToolMaterial.WOOD)).setUnlocalizedName("hatchetWood"));
/* 698 */     registerItem(272, "stone_sword", (new ItemSword(ToolMaterial.STONE)).setUnlocalizedName("swordStone"));
/* 699 */     registerItem(273, "stone_shovel", (new ItemSpade(ToolMaterial.STONE)).setUnlocalizedName("shovelStone"));
/* 700 */     registerItem(274, "stone_pickaxe", (new ItemPickaxe(ToolMaterial.STONE)).setUnlocalizedName("pickaxeStone"));
/* 701 */     registerItem(275, "stone_axe", (new ItemAxe(ToolMaterial.STONE)).setUnlocalizedName("hatchetStone"));
/* 702 */     registerItem(276, "diamond_sword", (new ItemSword(ToolMaterial.EMERALD)).setUnlocalizedName("swordDiamond"));
/* 703 */     registerItem(277, "diamond_shovel", (new ItemSpade(ToolMaterial.EMERALD)).setUnlocalizedName("shovelDiamond"));
/* 704 */     registerItem(278, "diamond_pickaxe", (new ItemPickaxe(ToolMaterial.EMERALD)).setUnlocalizedName("pickaxeDiamond"));
/* 705 */     registerItem(279, "diamond_axe", (new ItemAxe(ToolMaterial.EMERALD)).setUnlocalizedName("hatchetDiamond"));
/* 706 */     registerItem(280, "stick", (new Item()).setFull3D().setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabMaterials));
/* 707 */     registerItem(281, "bowl", (new Item()).setUnlocalizedName("bowl").setCreativeTab(CreativeTabs.tabMaterials));
/* 708 */     registerItem(282, "mushroom_stew", (new ItemSoup(6)).setUnlocalizedName("mushroomStew"));
/* 709 */     registerItem(283, "golden_sword", (new ItemSword(ToolMaterial.GOLD)).setUnlocalizedName("swordGold"));
/* 710 */     registerItem(284, "golden_shovel", (new ItemSpade(ToolMaterial.GOLD)).setUnlocalizedName("shovelGold"));
/* 711 */     registerItem(285, "golden_pickaxe", (new ItemPickaxe(ToolMaterial.GOLD)).setUnlocalizedName("pickaxeGold"));
/* 712 */     registerItem(286, "golden_axe", (new ItemAxe(ToolMaterial.GOLD)).setUnlocalizedName("hatchetGold"));
/* 713 */     registerItem(287, "string", (new ItemReed(Blocks.tripwire)).setUnlocalizedName("string").setCreativeTab(CreativeTabs.tabMaterials));
/* 714 */     registerItem(288, "feather", (new Item()).setUnlocalizedName("feather").setCreativeTab(CreativeTabs.tabMaterials));
/* 715 */     registerItem(289, "gunpowder", (new Item()).setUnlocalizedName("sulphur").setPotionEffect("+14&13-13").setCreativeTab(CreativeTabs.tabMaterials));
/* 716 */     registerItem(290, "wooden_hoe", (new ItemHoe(ToolMaterial.WOOD)).setUnlocalizedName("hoeWood"));
/* 717 */     registerItem(291, "stone_hoe", (new ItemHoe(ToolMaterial.STONE)).setUnlocalizedName("hoeStone"));
/* 718 */     registerItem(292, "iron_hoe", (new ItemHoe(ToolMaterial.IRON)).setUnlocalizedName("hoeIron"));
/* 719 */     registerItem(293, "diamond_hoe", (new ItemHoe(ToolMaterial.EMERALD)).setUnlocalizedName("hoeDiamond"));
/* 720 */     registerItem(294, "golden_hoe", (new ItemHoe(ToolMaterial.GOLD)).setUnlocalizedName("hoeGold"));
/* 721 */     registerItem(295, "wheat_seeds", (new ItemSeeds(Blocks.wheat, Blocks.farmland)).setUnlocalizedName("seeds"));
/* 722 */     registerItem(296, "wheat", (new Item()).setUnlocalizedName("wheat").setCreativeTab(CreativeTabs.tabMaterials));
/* 723 */     registerItem(297, "bread", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("bread"));
/* 724 */     registerItem(298, "leather_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 0)).setUnlocalizedName("helmetCloth"));
/* 725 */     registerItem(299, "leather_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 1)).setUnlocalizedName("chestplateCloth"));
/* 726 */     registerItem(300, "leather_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 2)).setUnlocalizedName("leggingsCloth"));
/* 727 */     registerItem(301, "leather_boots", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 3)).setUnlocalizedName("bootsCloth"));
/* 728 */     registerItem(302, "chainmail_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 0)).setUnlocalizedName("helmetChain"));
/* 729 */     registerItem(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 1)).setUnlocalizedName("chestplateChain"));
/* 730 */     registerItem(304, "chainmail_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 2)).setUnlocalizedName("leggingsChain"));
/* 731 */     registerItem(305, "chainmail_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 3)).setUnlocalizedName("bootsChain"));
/* 732 */     registerItem(306, "iron_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 0)).setUnlocalizedName("helmetIron"));
/* 733 */     registerItem(307, "iron_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 1)).setUnlocalizedName("chestplateIron"));
/* 734 */     registerItem(308, "iron_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 2)).setUnlocalizedName("leggingsIron"));
/* 735 */     registerItem(309, "iron_boots", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 3)).setUnlocalizedName("bootsIron"));
/* 736 */     registerItem(310, "diamond_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 0)).setUnlocalizedName("helmetDiamond"));
/* 737 */     registerItem(311, "diamond_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 1)).setUnlocalizedName("chestplateDiamond"));
/* 738 */     registerItem(312, "diamond_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 2)).setUnlocalizedName("leggingsDiamond"));
/* 739 */     registerItem(313, "diamond_boots", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 3)).setUnlocalizedName("bootsDiamond"));
/* 740 */     registerItem(314, "golden_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 0)).setUnlocalizedName("helmetGold"));
/* 741 */     registerItem(315, "golden_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 1)).setUnlocalizedName("chestplateGold"));
/* 742 */     registerItem(316, "golden_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 2)).setUnlocalizedName("leggingsGold"));
/* 743 */     registerItem(317, "golden_boots", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 3)).setUnlocalizedName("bootsGold"));
/* 744 */     registerItem(318, "flint", (new Item()).setUnlocalizedName("flint").setCreativeTab(CreativeTabs.tabMaterials));
/* 745 */     registerItem(319, "porkchop", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("porkchopRaw"));
/* 746 */     registerItem(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).setUnlocalizedName("porkchopCooked"));
/* 747 */     registerItem(321, "painting", (new ItemHangingEntity((Class)EntityPainting.class)).setUnlocalizedName("painting"));
/* 748 */     registerItem(322, "golden_apple", (new ItemAppleGold(4, 1.2F, false)).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 5, 1, 1.0F).setUnlocalizedName("appleGold"));
/* 749 */     registerItem(323, "sign", (new ItemSign()).setUnlocalizedName("sign"));
/* 750 */     registerItem(324, "wooden_door", (new ItemDoor(Blocks.oak_door)).setUnlocalizedName("doorOak"));
/* 751 */     Item item = (new ItemBucket(Blocks.air)).setUnlocalizedName("bucket").setMaxStackSize(16);
/* 752 */     registerItem(325, "bucket", item);
/* 753 */     registerItem(326, "water_bucket", (new ItemBucket((Block)Blocks.flowing_water)).setUnlocalizedName("bucketWater").setContainerItem(item));
/* 754 */     registerItem(327, "lava_bucket", (new ItemBucket((Block)Blocks.flowing_lava)).setUnlocalizedName("bucketLava").setContainerItem(item));
/* 755 */     registerItem(328, "minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.RIDEABLE)).setUnlocalizedName("minecart"));
/* 756 */     registerItem(329, "saddle", (new ItemSaddle()).setUnlocalizedName("saddle"));
/* 757 */     registerItem(330, "iron_door", (new ItemDoor(Blocks.iron_door)).setUnlocalizedName("doorIron"));
/* 758 */     registerItem(331, "redstone", (new ItemRedstone()).setUnlocalizedName("redstone").setPotionEffect("-5+6-7"));
/* 759 */     registerItem(332, "snowball", (new ItemSnowball()).setUnlocalizedName("snowball"));
/* 760 */     registerItem(333, "boat", (new ItemBoat()).setUnlocalizedName("boat"));
/* 761 */     registerItem(334, "leather", (new Item()).setUnlocalizedName("leather").setCreativeTab(CreativeTabs.tabMaterials));
/* 762 */     registerItem(335, "milk_bucket", (new ItemBucketMilk()).setUnlocalizedName("milk").setContainerItem(item));
/* 763 */     registerItem(336, "brick", (new Item()).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabMaterials));
/* 764 */     registerItem(337, "clay_ball", (new Item()).setUnlocalizedName("clay").setCreativeTab(CreativeTabs.tabMaterials));
/* 765 */     registerItem(338, "reeds", (new ItemReed((Block)Blocks.reeds)).setUnlocalizedName("reeds").setCreativeTab(CreativeTabs.tabMaterials));
/* 766 */     registerItem(339, "paper", (new Item()).setUnlocalizedName("paper").setCreativeTab(CreativeTabs.tabMisc));
/* 767 */     registerItem(340, "book", (new ItemBook()).setUnlocalizedName("book").setCreativeTab(CreativeTabs.tabMisc));
/* 768 */     registerItem(341, "slime_ball", (new Item()).setUnlocalizedName("slimeball").setCreativeTab(CreativeTabs.tabMisc));
/* 769 */     registerItem(342, "chest_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.CHEST)).setUnlocalizedName("minecartChest"));
/* 770 */     registerItem(343, "furnace_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.FURNACE)).setUnlocalizedName("minecartFurnace"));
/* 771 */     registerItem(344, "egg", (new ItemEgg()).setUnlocalizedName("egg"));
/* 772 */     registerItem(345, "compass", (new Item()).setUnlocalizedName("compass").setCreativeTab(CreativeTabs.tabTools));
/* 773 */     registerItem(346, "fishing_rod", (new ItemFishingRod()).setUnlocalizedName("fishingRod"));
/* 774 */     registerItem(347, "clock", (new Item()).setUnlocalizedName("clock").setCreativeTab(CreativeTabs.tabTools));
/* 775 */     registerItem(348, "glowstone_dust", (new Item()).setUnlocalizedName("yellowDust").setPotionEffect("+5-6-7").setCreativeTab(CreativeTabs.tabMaterials));
/* 776 */     registerItem(349, "fish", (new ItemFishFood(false)).setUnlocalizedName("fish").setHasSubtypes(true));
/* 777 */     registerItem(350, "cooked_fish", (new ItemFishFood(true)).setUnlocalizedName("fish").setHasSubtypes(true));
/* 778 */     registerItem(351, "dye", (new ItemDye()).setUnlocalizedName("dyePowder"));
/* 779 */     registerItem(352, "bone", (new Item()).setUnlocalizedName("bone").setFull3D().setCreativeTab(CreativeTabs.tabMisc));
/* 780 */     registerItem(353, "sugar", (new Item()).setUnlocalizedName("sugar").setPotionEffect("-0+1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabMaterials));
/* 781 */     registerItem(354, "cake", (new ItemReed(Blocks.cake)).setMaxStackSize(1).setUnlocalizedName("cake").setCreativeTab(CreativeTabs.tabFood));
/* 782 */     registerItem(355, "bed", (new ItemBed()).setMaxStackSize(1).setUnlocalizedName("bed"));
/* 783 */     registerItem(356, "repeater", (new ItemReed((Block)Blocks.unpowered_repeater)).setUnlocalizedName("diode").setCreativeTab(CreativeTabs.tabRedstone));
/* 784 */     registerItem(357, "cookie", (new ItemFood(2, 0.1F, false)).setUnlocalizedName("cookie"));
/* 785 */     registerItem(358, "filled_map", (new ItemMap()).setUnlocalizedName("map"));
/* 786 */     registerItem(359, "shears", (new ItemShears()).setUnlocalizedName("shears"));
/* 787 */     registerItem(360, "melon", (new ItemFood(2, 0.3F, false)).setUnlocalizedName("melon"));
/* 788 */     registerItem(361, "pumpkin_seeds", (new ItemSeeds(Blocks.pumpkin_stem, Blocks.farmland)).setUnlocalizedName("seeds_pumpkin"));
/* 789 */     registerItem(362, "melon_seeds", (new ItemSeeds(Blocks.melon_stem, Blocks.farmland)).setUnlocalizedName("seeds_melon"));
/* 790 */     registerItem(363, "beef", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("beefRaw"));
/* 791 */     registerItem(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).setUnlocalizedName("beefCooked"));
/* 792 */     registerItem(365, "chicken", (new ItemFood(2, 0.3F, true)).setPotionEffect(Potion.hunger.id, 30, 0, 0.3F).setUnlocalizedName("chickenRaw"));
/* 793 */     registerItem(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).setUnlocalizedName("chickenCooked"));
/* 794 */     registerItem(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F).setUnlocalizedName("rottenFlesh"));
/* 795 */     registerItem(368, "ender_pearl", (new ItemEnderPearl()).setUnlocalizedName("enderPearl"));
/* 796 */     registerItem(369, "blaze_rod", (new Item()).setUnlocalizedName("blazeRod").setCreativeTab(CreativeTabs.tabMaterials).setFull3D());
/* 797 */     registerItem(370, "ghast_tear", (new Item()).setUnlocalizedName("ghastTear").setPotionEffect("+0-1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 798 */     registerItem(371, "gold_nugget", (new Item()).setUnlocalizedName("goldNugget").setCreativeTab(CreativeTabs.tabMaterials));
/* 799 */     registerItem(372, "nether_wart", (new ItemSeeds(Blocks.nether_wart, Blocks.soul_sand)).setUnlocalizedName("netherStalkSeeds").setPotionEffect("+4"));
/* 800 */     registerItem(373, "potion", (new ItemPotion()).setUnlocalizedName("potion"));
/* 801 */     registerItem(374, "glass_bottle", (new ItemGlassBottle()).setUnlocalizedName("glassBottle"));
/* 802 */     registerItem(375, "spider_eye", (new ItemFood(2, 0.8F, false)).setPotionEffect(Potion.poison.id, 5, 0, 1.0F).setUnlocalizedName("spiderEye").setPotionEffect("-0-1+2-3&4-4+13"));
/* 803 */     registerItem(376, "fermented_spider_eye", (new Item()).setUnlocalizedName("fermentedSpiderEye").setPotionEffect("-0+3-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 804 */     registerItem(377, "blaze_powder", (new Item()).setUnlocalizedName("blazePowder").setPotionEffect("+0-1-2+3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 805 */     registerItem(378, "magma_cream", (new Item()).setUnlocalizedName("magmaCream").setPotionEffect("+0+1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 806 */     registerItem(379, "brewing_stand", (new ItemReed(Blocks.brewing_stand)).setUnlocalizedName("brewingStand").setCreativeTab(CreativeTabs.tabBrewing));
/* 807 */     registerItem(380, "cauldron", (new ItemReed((Block)Blocks.cauldron)).setUnlocalizedName("cauldron").setCreativeTab(CreativeTabs.tabBrewing));
/* 808 */     registerItem(381, "ender_eye", (new ItemEnderEye()).setUnlocalizedName("eyeOfEnder"));
/* 809 */     registerItem(382, "speckled_melon", (new Item()).setUnlocalizedName("speckledMelon").setPotionEffect("+0-1+2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 810 */     registerItem(383, "spawn_egg", (new ItemMonsterPlacer()).setUnlocalizedName("monsterPlacer"));
/* 811 */     registerItem(384, "experience_bottle", (new ItemExpBottle()).setUnlocalizedName("expBottle"));
/* 812 */     registerItem(385, "fire_charge", (new ItemFireball()).setUnlocalizedName("fireball"));
/* 813 */     registerItem(386, "writable_book", (new ItemWritableBook()).setUnlocalizedName("writingBook").setCreativeTab(CreativeTabs.tabMisc));
/* 814 */     registerItem(387, "written_book", (new ItemEditableBook()).setUnlocalizedName("writtenBook").setMaxStackSize(16));
/* 815 */     registerItem(388, "emerald", (new Item()).setUnlocalizedName("emerald").setCreativeTab(CreativeTabs.tabMaterials));
/* 816 */     registerItem(389, "item_frame", (new ItemHangingEntity((Class)EntityItemFrame.class)).setUnlocalizedName("frame"));
/* 817 */     registerItem(390, "flower_pot", (new ItemReed(Blocks.flower_pot)).setUnlocalizedName("flowerPot").setCreativeTab(CreativeTabs.tabDecorations));
/* 818 */     registerItem(391, "carrot", (new ItemSeedFood(3, 0.6F, Blocks.carrots, Blocks.farmland)).setUnlocalizedName("carrots"));
/* 819 */     registerItem(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.potatoes, Blocks.farmland)).setUnlocalizedName("potato"));
/* 820 */     registerItem(393, "baked_potato", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("potatoBaked"));
/* 821 */     registerItem(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).setPotionEffect(Potion.poison.id, 5, 0, 0.6F).setUnlocalizedName("potatoPoisonous"));
/* 822 */     registerItem(395, "map", (new ItemEmptyMap()).setUnlocalizedName("emptyMap"));
/* 823 */     registerItem(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).setUnlocalizedName("carrotGolden").setPotionEffect("-0+1+2-3+13&4-4").setCreativeTab(CreativeTabs.tabBrewing));
/* 824 */     registerItem(397, "skull", (new ItemSkull()).setUnlocalizedName("skull"));
/* 825 */     registerItem(398, "carrot_on_a_stick", (new ItemCarrotOnAStick()).setUnlocalizedName("carrotOnAStick"));
/* 826 */     registerItem(399, "nether_star", (new ItemSimpleFoiled()).setUnlocalizedName("netherStar").setCreativeTab(CreativeTabs.tabMaterials));
/* 827 */     registerItem(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).setUnlocalizedName("pumpkinPie").setCreativeTab(CreativeTabs.tabFood));
/* 828 */     registerItem(401, "fireworks", (new ItemFirework()).setUnlocalizedName("fireworks"));
/* 829 */     registerItem(402, "firework_charge", (new ItemFireworkCharge()).setUnlocalizedName("fireworksCharge").setCreativeTab(CreativeTabs.tabMisc));
/* 830 */     registerItem(403, "enchanted_book", (new ItemEnchantedBook()).setMaxStackSize(1).setUnlocalizedName("enchantedBook"));
/* 831 */     registerItem(404, "comparator", (new ItemReed((Block)Blocks.unpowered_comparator)).setUnlocalizedName("comparator").setCreativeTab(CreativeTabs.tabRedstone));
/* 832 */     registerItem(405, "netherbrick", (new Item()).setUnlocalizedName("netherbrick").setCreativeTab(CreativeTabs.tabMaterials));
/* 833 */     registerItem(406, "quartz", (new Item()).setUnlocalizedName("netherquartz").setCreativeTab(CreativeTabs.tabMaterials));
/* 834 */     registerItem(407, "tnt_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.TNT)).setUnlocalizedName("minecartTnt"));
/* 835 */     registerItem(408, "hopper_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.HOPPER)).setUnlocalizedName("minecartHopper"));
/* 836 */     registerItem(409, "prismarine_shard", (new Item()).setUnlocalizedName("prismarineShard").setCreativeTab(CreativeTabs.tabMaterials));
/* 837 */     registerItem(410, "prismarine_crystals", (new Item()).setUnlocalizedName("prismarineCrystals").setCreativeTab(CreativeTabs.tabMaterials));
/* 838 */     registerItem(411, "rabbit", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("rabbitRaw"));
/* 839 */     registerItem(412, "cooked_rabbit", (new ItemFood(5, 0.6F, true)).setUnlocalizedName("rabbitCooked"));
/* 840 */     registerItem(413, "rabbit_stew", (new ItemSoup(10)).setUnlocalizedName("rabbitStew"));
/* 841 */     registerItem(414, "rabbit_foot", (new Item()).setUnlocalizedName("rabbitFoot").setPotionEffect("+0+1-2+3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/* 842 */     registerItem(415, "rabbit_hide", (new Item()).setUnlocalizedName("rabbitHide").setCreativeTab(CreativeTabs.tabMaterials));
/* 843 */     registerItem(416, "armor_stand", (new ItemArmorStand()).setUnlocalizedName("armorStand").setMaxStackSize(16));
/* 844 */     registerItem(417, "iron_horse_armor", (new Item()).setUnlocalizedName("horsearmormetal").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 845 */     registerItem(418, "golden_horse_armor", (new Item()).setUnlocalizedName("horsearmorgold").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 846 */     registerItem(419, "diamond_horse_armor", (new Item()).setUnlocalizedName("horsearmordiamond").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 847 */     registerItem(420, "lead", (new ItemLead()).setUnlocalizedName("leash"));
/* 848 */     registerItem(421, "name_tag", (new ItemNameTag()).setUnlocalizedName("nameTag"));
/* 849 */     registerItem(422, "command_block_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.COMMAND_BLOCK)).setUnlocalizedName("minecartCommandBlock").setCreativeTab(null));
/* 850 */     registerItem(423, "mutton", (new ItemFood(2, 0.3F, true)).setUnlocalizedName("muttonRaw"));
/* 851 */     registerItem(424, "cooked_mutton", (new ItemFood(6, 0.8F, true)).setUnlocalizedName("muttonCooked"));
/* 852 */     registerItem(425, "banner", (new ItemBanner()).setUnlocalizedName("banner"));
/* 853 */     registerItem(427, "spruce_door", (new ItemDoor(Blocks.spruce_door)).setUnlocalizedName("doorSpruce"));
/* 854 */     registerItem(428, "birch_door", (new ItemDoor(Blocks.birch_door)).setUnlocalizedName("doorBirch"));
/* 855 */     registerItem(429, "jungle_door", (new ItemDoor(Blocks.jungle_door)).setUnlocalizedName("doorJungle"));
/* 856 */     registerItem(430, "acacia_door", (new ItemDoor(Blocks.acacia_door)).setUnlocalizedName("doorAcacia"));
/* 857 */     registerItem(431, "dark_oak_door", (new ItemDoor(Blocks.dark_oak_door)).setUnlocalizedName("doorDarkOak"));
/* 858 */     registerItem(2256, "record_13", (new ItemRecord("13")).setUnlocalizedName("record"));
/* 859 */     registerItem(2257, "record_cat", (new ItemRecord("cat")).setUnlocalizedName("record"));
/* 860 */     registerItem(2258, "record_blocks", (new ItemRecord("blocks")).setUnlocalizedName("record"));
/* 861 */     registerItem(2259, "record_chirp", (new ItemRecord("chirp")).setUnlocalizedName("record"));
/* 862 */     registerItem(2260, "record_far", (new ItemRecord("far")).setUnlocalizedName("record"));
/* 863 */     registerItem(2261, "record_mall", (new ItemRecord("mall")).setUnlocalizedName("record"));
/* 864 */     registerItem(2262, "record_mellohi", (new ItemRecord("mellohi")).setUnlocalizedName("record"));
/* 865 */     registerItem(2263, "record_stal", (new ItemRecord("stal")).setUnlocalizedName("record"));
/* 866 */     registerItem(2264, "record_strad", (new ItemRecord("strad")).setUnlocalizedName("record"));
/* 867 */     registerItem(2265, "record_ward", (new ItemRecord("ward")).setUnlocalizedName("record"));
/* 868 */     registerItem(2266, "record_11", (new ItemRecord("11")).setUnlocalizedName("record"));
/* 869 */     registerItem(2267, "record_wait", (new ItemRecord("wait")).setUnlocalizedName("record"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerItemBlock(Block blockIn) {
/* 876 */     registerItemBlock(blockIn, new ItemBlock(blockIn));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void registerItemBlock(Block blockIn, Item itemIn) {
/* 883 */     registerItem(Block.getIdFromBlock(blockIn), (ResourceLocation)Block.blockRegistry.getNameForObject(blockIn), itemIn);
/* 884 */     BLOCK_TO_ITEM.put(blockIn, itemIn);
/*     */   }
/*     */   
/*     */   private static void registerItem(int id, String textualID, Item itemIn) {
/* 888 */     registerItem(id, new ResourceLocation(textualID), itemIn);
/*     */   }
/*     */   
/*     */   private static void registerItem(int id, ResourceLocation textualID, Item itemIn) {
/* 892 */     itemRegistry.register(id, textualID, itemIn);
/*     */   }
/*     */   
/*     */   public enum ToolMaterial {
/* 896 */     WOOD(0, 59, 2.0F, 0.0F, 15),
/* 897 */     STONE(1, 131, 4.0F, 1.0F, 5),
/* 898 */     IRON(2, 250, 6.0F, 2.0F, 14),
/* 899 */     EMERALD(3, 1561, 8.0F, 3.0F, 10),
/* 900 */     GOLD(0, 32, 12.0F, 0.0F, 22);
/*     */     
/*     */     private final int harvestLevel;
/*     */     private final int maxUses;
/*     */     private final float efficiencyOnProperMaterial;
/*     */     private final float damageVsEntity;
/*     */     private final int enchantability;
/*     */     
/*     */     ToolMaterial(int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability) {
/* 909 */       this.harvestLevel = harvestLevel;
/* 910 */       this.maxUses = maxUses;
/* 911 */       this.efficiencyOnProperMaterial = efficiency;
/* 912 */       this.damageVsEntity = damageVsEntity;
/* 913 */       this.enchantability = enchantability;
/*     */     }
/*     */     
/*     */     public int getMaxUses() {
/* 917 */       return this.maxUses;
/*     */     }
/*     */     
/*     */     public float getEfficiencyOnProperMaterial() {
/* 921 */       return this.efficiencyOnProperMaterial;
/*     */     }
/*     */     
/*     */     public float getDamageVsEntity() {
/* 925 */       return this.damageVsEntity;
/*     */     }
/*     */     
/*     */     public int getHarvestLevel() {
/* 929 */       return this.harvestLevel;
/*     */     }
/*     */     
/*     */     public int getEnchantability() {
/* 933 */       return this.enchantability;
/*     */     }
/*     */     
/*     */     public Item getRepairItem() {
/* 937 */       return (this == WOOD) ? Item.getItemFromBlock(Blocks.planks) : ((this == STONE) ? Item.getItemFromBlock(Blocks.cobblestone) : ((this == GOLD) ? Items.gold_ingot : ((this == IRON) ? Items.iron_ingot : ((this == EMERALD) ? Items.diamond : null))));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\item\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */