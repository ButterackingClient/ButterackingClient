package io.netty.util.concurrent;

public interface GenericProgressiveFutureListener<F extends ProgressiveFuture<?>> extends GenericFutureListener<F> {
  void operationProgressed(F paramF, long paramLong1, long paramLong2) throws Exception;
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\nett\\util\concurrent\GenericProgressiveFutureListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */