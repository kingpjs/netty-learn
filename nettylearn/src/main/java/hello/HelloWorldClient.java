package hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author honghao.zhang
 * Created on 2020-04-13 11:35
 */
public class HelloWorldClient {

    static final String HOST = "127.0.0.1";

    static final int PORT = 8899;

    static final int SIZE = 256;

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new HelloWordlClientSimpleHandler());
                            pipeline.addLast(new BaseClient1Handler());
                            pipeline.addLast(new BaseClient2Handler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            future.channel().writeAndFlush("Hello Netty Server,I'm client");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
