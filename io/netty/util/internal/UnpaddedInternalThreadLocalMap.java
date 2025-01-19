/*    */ package io.netty.util.internal;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.CharsetDecoder;
/*    */ import java.nio.charset.CharsetEncoder;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
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
/*    */ class UnpaddedInternalThreadLocalMap
/*    */ {
/*    */   static ThreadLocal<InternalThreadLocalMap> slowThreadLocalMap;
/* 35 */   static final AtomicInteger nextIndex = new AtomicInteger();
/*    */   
/*    */   Object[] indexedVariables;
/*    */   
/*    */   int futureListenerStackDepth;
/*    */   
/*    */   int localChannelReaderStackDepth;
/*    */   
/*    */   Map<Class<?>, Boolean> handlerSharableCache;
/*    */   
/*    */   IntegerHolder counterHashCode;
/*    */   
/*    */   ThreadLocalRandom random;
/*    */   Map<Class<?>, TypeParameterMatcher> typeParameterMatcherGetCache;
/*    */   Map<Class<?>, Map<String, TypeParameterMatcher>> typeParameterMatcherFindCache;
/*    */   StringBuilder stringBuilder;
/*    */   Map<Charset, CharsetEncoder> charsetEncoderCache;
/*    */   Map<Charset, CharsetDecoder> charsetDecoderCache;
/*    */   
/*    */   UnpaddedInternalThreadLocalMap(Object[] indexedVariables) {
/* 55 */     this.indexedVariables = indexedVariables;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\nett\\util\internal\UnpaddedInternalThreadLocalMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */