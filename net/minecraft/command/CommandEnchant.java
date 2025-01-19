/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandEnchant
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*  17 */     return "enchant";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  24 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  31 */     return "commands.enchant.usage";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     int i;
/*  38 */     if (args.length < 2) {
/*  39 */       throw new WrongUsageException("commands.enchant.usage", new Object[0]);
/*     */     }
/*  41 */     EntityPlayerMP entityPlayerMP = getPlayer(sender, args[0]);
/*  42 */     sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
/*     */ 
/*     */     
/*     */     try {
/*  46 */       i = parseInt(args[1], 0);
/*  47 */     } catch (NumberInvalidException numberinvalidexception) {
/*  48 */       Enchantment enchantment = Enchantment.getEnchantmentByLocation(args[1]);
/*     */       
/*  50 */       if (enchantment == null) {
/*  51 */         throw numberinvalidexception;
/*     */       }
/*     */       
/*  54 */       i = enchantment.effectId;
/*     */     } 
/*     */     
/*  57 */     int j = 1;
/*  58 */     ItemStack itemstack = entityPlayerMP.getCurrentEquippedItem();
/*     */     
/*  60 */     if (itemstack == null) {
/*  61 */       throw new CommandException("commands.enchant.noItem", new Object[0]);
/*     */     }
/*  63 */     Enchantment enchantment1 = Enchantment.getEnchantmentById(i);
/*     */     
/*  65 */     if (enchantment1 == null)
/*  66 */       throw new NumberInvalidException("commands.enchant.notFound", new Object[] { Integer.valueOf(i) }); 
/*  67 */     if (!enchantment1.canApply(itemstack)) {
/*  68 */       throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
/*     */     }
/*  70 */     if (args.length >= 3) {
/*  71 */       j = parseInt(args[2], enchantment1.getMinLevel(), enchantment1.getMaxLevel());
/*     */     }
/*     */     
/*  74 */     if (itemstack.hasTagCompound()) {
/*  75 */       NBTTagList nbttaglist = itemstack.getEnchantmentTagList();
/*     */       
/*  77 */       if (nbttaglist != null) {
/*  78 */         for (int k = 0; k < nbttaglist.tagCount(); k++) {
/*  79 */           int l = nbttaglist.getCompoundTagAt(k).getShort("id");
/*     */           
/*  81 */           if (Enchantment.getEnchantmentById(l) != null) {
/*  82 */             Enchantment enchantment2 = Enchantment.getEnchantmentById(l);
/*     */             
/*  84 */             if (!enchantment2.canApplyTogether(enchantment1)) {
/*  85 */               throw new CommandException("commands.enchant.cantCombine", new Object[] { enchantment1.getTranslatedName(j), enchantment2.getTranslatedName(nbttaglist.getCompoundTagAt(k).getShort("lvl")) });
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  92 */     itemstack.addEnchantment(enchantment1, j);
/*  93 */     notifyOperators(sender, this, "commands.enchant.success", new Object[0]);
/*  94 */     sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 101 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, getListOfPlayers()) : ((args.length == 2) ? getListOfStringsMatchingLastWord(args, Enchantment.func_181077_c()) : null);
/*     */   }
/*     */   
/*     */   protected String[] getListOfPlayers() {
/* 105 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 112 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\command\CommandEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */