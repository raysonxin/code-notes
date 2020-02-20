package com.raysonxin.rpc.protocol;

import com.alibaba.fastjson.JSON;
import com.raysonxin.rpc.protocol.serialize.FastJsonSerializer;
import com.raysonxin.rpc.utils.SerialNumberUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: TestProtocolClient.java
 * @author: raysonxin
 * @date: 2020/2/11 8:44 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Slf4j
public class TestProtocolClient {

    public static void main(String[] args) throws Exception {
        int port = 9092;
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            bootstrap.option(ChannelOption.TCP_NODELAY, Boolean.TRUE);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
                    socketChannel.pipeline().addLast(new ReqeustMessagePacketEncoder(FastJsonSerializer.X));
                    socketChannel.pipeline().addLast(new ResponseMessagePacketDecoder());
                    socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<ResponseMessagePacket>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponseMessagePacket packet) throws Exception {
                            Object targetPayload = packet.getPayload();
                            if (targetPayload instanceof ByteBuf) {
                                ByteBuf byteBuf = (ByteBuf) targetPayload;
                                int readableByteLength = byteBuf.readableBytes();
                                byte[] bytes = new byte[readableByteLength];
                                byteBuf.readBytes(bytes);
                                targetPayload = FastJsonSerializer.X.decode(bytes, String.class);
                                byteBuf.release();
                            }
                            packet.setPayload(targetPayload);
                            log.info("recv msg from server,msg content:{}", JSON.toJSONString(packet));
                        }
                    });
                }
            });

            ChannelFuture future = bootstrap.connect("localhost", port).sync();
            log.info("Start Netty Client Success...");

            Channel channel = future.channel();
            RequestMessagePacket packet = new RequestMessagePacket();
            packet.setMagicNumber(ProtocolConstant.MAGIC_NUMBER);
            packet.setVersion(ProtocolConstant.VERSION);
            packet.setSerialNumber(SerialNumberUtils.X.generateSerialNumber());
            packet.setMessageType(MessageType.REQUEST);
            packet.setInterfaceName("com.raysonxin.rpc.contract.HelloService");
            packet.setMethodName("sayHello");
            packet.setMethodArgumentSignatures(new String[]{"java.lang.String"});
            packet.setMethodArguments(new Object[]{"raysonxin"});
            channel.writeAndFlush(packet);
            future.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

}
