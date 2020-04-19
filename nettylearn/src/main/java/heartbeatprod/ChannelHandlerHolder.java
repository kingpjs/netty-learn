package heartbeatprod;

import io.netty.channel.ChannelHandler;

/**
 * @author honghao.zhang
 * Created on 2020-04-19 21:27
 */
public interface ChannelHandlerHolder {

    ChannelHandler[] handlers();
}
