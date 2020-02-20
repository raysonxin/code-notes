package com.raysonxin.rpc.protocol;

import com.alibaba.fastjson.JSON;
import com.raysonxin.rpc.protocol.serialize.FastJsonSerializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: TestProtocolServer.java
 * @author: raysonxin
 * @date: 2020/2/11 8:32 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Slf4j
public class TestProtocolServer {

    public static void main(String[] args) throws Exception {
        int port = 9092;
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
                            socketChannel.pipeline().addLast(new RequestMessagePacketDecoder());
                            socketChannel.pipeline().addLast(new ResponseMessagePacketEncoder(FastJsonSerializer.X));
                            socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<RequestMessagePacket>() {

                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, RequestMessagePacket packet) throws Exception {
                                    log.info("recv msg from client,msg conteng:{}", JSON.toJSONString(packet));
                                    ResponseMessagePacket response = new ResponseMessagePacket();
                                    response.setMagicNumber(packet.getMagicNumber());
                                    response.setVersion(packet.getVersion());
                                    response.setSerialNumber(packet.getSerialNumber());
                                    response.setAttachments(packet.getAttachments());
                                    response.setMessageType(MessageType.RESPONSE);
                                    response.setErrorCode(200L);
                                    response.setMessage("Success");
                                    response.setPayload("{\"name\":\"rsx-rpc\"}");
                                    ctx.writeAndFlush(response);
                                }
                            });
                        }
                    });

            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("Start Netty Server[{}] Success...", port);
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
