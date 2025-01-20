/*     */ package io.netty.handler.codec.http;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URLEncoder;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryStringEncoder
/*     */ {
/*     */   private final Charset charset;
/*     */   private final String uri;
/*  42 */   private final List<Param> params = new ArrayList<Param>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryStringEncoder(String uri) {
/*  49 */     this(uri, HttpConstants.DEFAULT_CHARSET);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryStringEncoder(String uri, Charset charset) {
/*  57 */     if (uri == null) {
/*  58 */       throw new NullPointerException("getUri");
/*     */     }
/*  60 */     if (charset == null) {
/*  61 */       throw new NullPointerException("charset");
/*     */     }
/*     */     
/*  64 */     this.uri = uri;
/*  65 */     this.charset = charset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addParam(String name, String value) {
/*  72 */     if (name == null) {
/*  73 */       throw new NullPointerException("name");
/*     */     }
/*  75 */     this.params.add(new Param(name, value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI toUri() throws URISyntaxException {
/*  84 */     return new URI(toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  94 */     if (this.params.isEmpty()) {
/*  95 */       return this.uri;
/*     */     }
/*  97 */     StringBuilder sb = (new StringBuilder(this.uri)).append('?');
/*  98 */     for (int i = 0; i < this.params.size(); i++) {
/*  99 */       Param param = this.params.get(i);
/* 100 */       sb.append(encodeComponent(param.name, this.charset));
/* 101 */       if (param.value != null) {
/* 102 */         sb.append('=');
/* 103 */         sb.append(encodeComponent(param.value, this.charset));
/*     */       } 
/* 105 */       if (i != this.params.size() - 1) {
/* 106 */         sb.append('&');
/*     */       }
/*     */     } 
/* 109 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String encodeComponent(String s, Charset charset) {
/*     */     try {
/* 116 */       return URLEncoder.encode(s, charset.name()).replace("+", "%20");
/* 117 */     } catch (UnsupportedEncodingException ignored) {
/* 118 */       throw new UnsupportedCharsetException(charset.name());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Param
/*     */   {
/*     */     final String name;
/*     */     final String value;
/*     */     
/*     */     Param(String name, String value) {
/* 128 */       this.value = value;
/* 129 */       this.name = name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\http\QueryStringEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */