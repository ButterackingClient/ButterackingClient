/*    */ package org.newdawn.slick.opengl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompositeIOException
/*    */   extends IOException
/*    */ {
/* 13 */   private ArrayList exceptions = new ArrayList();
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
/*    */   public void addException(Exception e) {
/* 28 */     this.exceptions.add(e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 35 */     String msg = "Composite Exception: \n";
/* 36 */     for (int i = 0; i < this.exceptions.size(); i++) {
/* 37 */       msg = msg + "\t" + ((IOException)this.exceptions.get(i)).getMessage() + "\n";
/*    */     }
/*    */     
/* 40 */     return msg;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\org\newdawn\slick\opengl\CompositeIOException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */