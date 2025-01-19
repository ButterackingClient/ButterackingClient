/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.WrongUsageException;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandBroadcast
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 19 */     return "say";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 26 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 33 */     return "commands.say.usage";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 40 */     if (args.length > 0 && args[0].length() > 0) {
/* 41 */       IChatComponent ichatcomponent = getChatComponentFromNthArg(sender, args, 0, true);
/* 42 */       MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation("chat.type.announcement", new Object[] { sender.getDisplayName(), ichatcomponent }));
/*    */     } else {
/* 44 */       throw new WrongUsageException("commands.say.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 49 */     return (args.length >= 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\command\server\CommandBroadcast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */