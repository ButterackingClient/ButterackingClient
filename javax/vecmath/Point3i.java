/*    */ package javax.vecmath;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class Point3i
/*    */   extends Tuple3i
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = 6149289077348153921L;
/*    */   
/*    */   public Point3i(int x, int y, int z) {
/* 50 */     super(x, y, z);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point3i(int[] t) {
/* 60 */     super(t);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point3i(Tuple3i t1) {
/* 71 */     super(t1);
/*    */   }
/*    */   
/*    */   public Point3i() {}
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\javax\vecmath\Point3i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */