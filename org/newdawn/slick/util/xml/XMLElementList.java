/*    */ package org.newdawn.slick.util.xml;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLElementList
/*    */ {
/* 13 */   private ArrayList list = new ArrayList();
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
/*    */   public void add(XMLElement element) {
/* 28 */     this.list.add(element);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 37 */     return this.list.size();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XMLElement get(int i) {
/* 47 */     return this.list.get(i);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(XMLElement element) {
/* 57 */     return this.list.contains(element);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAllTo(Collection collection) {
/* 66 */     collection.addAll(this.list);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\org\newdawn\slic\\util\xml\XMLElementList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */