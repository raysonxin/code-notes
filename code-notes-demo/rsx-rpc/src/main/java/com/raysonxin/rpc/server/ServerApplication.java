package com.raysonxin.rpc.server;

import com.raysonxin.rpc.protocol.RequestMessagePacketDecoder;
import com.raysonxin.rpc.protocol.ResponseMessagePacketEncoder;
import com.raysonxin.rpc.protocol.serialize.FastJsonSerializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @className: ServerApplication.java
 * @author: raysonxin
 * @date: 2020/2/15 8:14 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@SpringBootApplication(scanBasePackages = "com.raysonxin.rpc.server")
@Slf4j
public class ServerApplication implements CommandLineRunner {

    @Value("${netty.port:9092}")
    private Integer nettyPort;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int port = nettyPort;
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                            socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
                            socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            socketChannel.pipeline().addLast(new RequestMessagePacketDecoder());
                            socketChannel.pipeline().addLast(new ResponseMessagePacketEncoder(FastJsonSerializer.X));
                            socketChannel.pipeline().addLast(applicationContext.getBean(ServerHandler.class));
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("启动NettyServer[{}]成功...", port);
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
