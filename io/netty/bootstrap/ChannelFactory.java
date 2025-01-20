package io.netty.bootstrap;

public interface ChannelFactory<T extends io.netty.channel.Channel> {
  T newChannel();
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\bootstrap\ChannelFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */