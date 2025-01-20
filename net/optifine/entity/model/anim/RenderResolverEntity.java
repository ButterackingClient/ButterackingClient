/*    */ package net.optifine.entity.model.anim;
/*    */ 
/*    */ import net.optifine.expr.IExpression;
/*    */ 
/*    */ public class RenderResolverEntity implements IRenderResolver {
/*    */   public IExpression getParameter(String name) {
/*  7 */     RenderEntityParameterBool renderentityparameterbool = RenderEntityParameterBool.parse(name);
/*    */     
/*  9 */     if (renderentityparameterbool != null) {
/* 10 */       return (IExpression)renderentityparameterbool;
/*    */     }
/* 12 */     RenderEntityParameterFloat renderentityparameterfloat = RenderEntityParameterFloat.parse(name);
/* 13 */     return (renderentityparameterfloat != null) ? (IExpression)renderentityparameterfloat : null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\optifine\entity\model\anim\RenderResolverEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */