package io.netty.channel;

import io.netty.util.concurrent.EventExecutorGroup;

public interface EventLoopGroup extends EventExecutorGroup {
  EventLoop next();
  
  ChannelFuture register(Channel paramChannel);
  
  ChannelFuture register(Channel paramChannel, ChannelPromise paramChannelPromise);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\channel\EventLoopGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */