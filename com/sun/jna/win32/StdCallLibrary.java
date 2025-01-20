/*    */ package com.sun.jna.win32;
/*    */ 
/*    */ import com.sun.jna.Callback;
/*    */ import com.sun.jna.FunctionMapper;
/*    */ import com.sun.jna.Library;
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
/*    */ public interface StdCallLibrary
/*    */   extends Library, StdCall
/*    */ {
/*    */   public static final int STDCALL_CONVENTION = 63;
/* 36 */   public static final FunctionMapper FUNCTION_MAPPER = new StdCallFunctionMapper();
/*    */   
/*    */   public static interface StdCallCallback extends Callback, StdCall {}
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\com\sun\jna\win32\StdCallLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */