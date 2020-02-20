package com.raysonxin.rpc.protocol;

import com.raysonxin.rpc.protocol.serialize.Serializer;
import com.raysonxin.rpc.utils.ByteBufferUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.RequiredArgsConstructor;

/**
 * @className: ResponseMessagePacketEncoder.java
 * @author: raysonxin
 * @date: 2020/2/11 8:21 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@RequiredArgsConstructor
public class ResponseMessagePacketEncoder extends MessageToByteEncoder<ResponseMessagePacket> {

    private final Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext context, ResponseMessagePacket packet, ByteBuf out) throws Exception {
        packet.encode(out);
        out.writeLong(packet.getErrorCode());
        String message = packet.getMessage();
        ByteBufferUtils.X.encodeUtf8CharSequence(out, message);
        byte[] bytes = serializer.encode(packet.getPayload());
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
