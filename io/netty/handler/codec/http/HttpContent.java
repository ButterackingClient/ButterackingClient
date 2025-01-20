package io.netty.handler.codec.http;

import io.netty.buffer.ByteBufHolder;

public interface HttpContent extends HttpObject, ByteBufHolder {
  HttpContent copy();
  
  HttpContent duplicate();
  
  HttpContent retain();
  
  HttpContent retain(int paramInt);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\http\HttpContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */