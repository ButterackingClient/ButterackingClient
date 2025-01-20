/*    */ package net.optifine.expr;
/*    */ 
/*    */ public class ExpressionFloatCached implements IExpressionFloat, IExpressionCached {
/*    */   private IExpressionFloat expression;
/*    */   private boolean cached;
/*    */   private float value;
/*    */   
/*    */   public ExpressionFloatCached(IExpressionFloat expression) {
/*  9 */     this.expression = expression;
/*    */   }
/*    */   
/*    */   public float eval() {
/* 13 */     if (!this.cached) {
/* 14 */       this.value = this.expression.eval();
/* 15 */       this.cached = true;
/*    */     } 
/*    */     
/* 18 */     return this.value;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 22 */     this.cached = false;
/*    */   }
/*    */   
/*    */   public ExpressionType getExpressionType() {
/* 26 */     return ExpressionType.FLOAT;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     return "cached(" + this.expression + ")";
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\optifine\expr\ExpressionFloatCached.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */