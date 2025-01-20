/*    */ package net.arikia.dev.drpc;
/*    */ 
/*    */ import com.sun.jna.Structure;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DiscordUser
/*    */   extends Structure
/*    */ {
/*    */   public String userId;
/*    */   public String username;
/*    */   public String discriminator;
/*    */   public String avatar;
/*    */   
/*    */   public List<String> getFieldOrder() {
/* 37 */     return Arrays.asList(new String[] { "userId", "username", "discriminator", "avatar" });
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\arikia\dev\drpc\DiscordUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */