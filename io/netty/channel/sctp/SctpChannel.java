package io.netty.channel.sctp;

import com.sun.nio.sctp.Association;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

public interface SctpChannel extends Channel {
  SctpServerChannel parent();
  
  Association association();
  
  InetSocketAddress localAddress();
  
  Set<InetSocketAddress> allLocalAddresses();
  
  SctpChannelConfig config();
  
  InetSocketAddress remoteAddress();
  
  Set<InetSocketAddress> allRemoteAddresses();
  
  ChannelFuture bindAddress(InetAddress paramInetAddress);
  
  ChannelFuture bindAddress(InetAddress paramInetAddress, ChannelPromise paramChannelPromise);
  
  ChannelFuture unbindAddress(InetAddress paramInetAddress);
  
  ChannelFuture unbindAddress(InetAddress paramInetAddress, ChannelPromise paramChannelPromise);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\channel\sctp\SctpChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */