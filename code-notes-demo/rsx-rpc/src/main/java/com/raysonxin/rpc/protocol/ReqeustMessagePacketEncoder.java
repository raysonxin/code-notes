package com.raysonxin.rpc.protocol;

import com.raysonxin.rpc.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.RequiredArgsConstructor;

/**
 * @className: ReqeustMessagePacketEncoder.java
 * @author: raysonxin
 * @date: 2020/2/11 4:14 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@RequiredArgsConstructor
public class ReqeustMessagePacketEncoder extends MessageToByteEncoder<RequestMessagePacket> {

    private final Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext context, RequestMessagePacket packet, ByteBuf out) throws Exception {
        packet.encode(out);

        out.writeInt(packet.getInterfaceName().length());
        out.writeCharSequence(packet.getInterfaceName(), ProtocolConstant.UTF_8);

        out.writeInt(packet.getMethodName().length());
        out.writeCharSequence(packet.getMethodName(), ProtocolConstant.UTF_8);

        if (null != packet.getMethodArgumentSignatures()) {
            int len = packet.getMethodArgumentSignatures().length;
            out.writeInt(len);
            for (int i = 0; i < len; i++) {
                String methodArguementSignature = packet.getMethodArgumentSignatures()[i];
                out.writeInt(methodArguementSignature.length());
                out.writeCharSequence(methodArguementSignature, ProtocolConstant.UTF_8);
            }
        } else {
            out.writeInt(0);
        }

        if (null != packet.getMethodArguments()) {
            int len = packet.getMethodArguments().length;
            out.writeInt(len);
            for (int i = 0; i < len; i++) {
                byte[] bytes = serializer.encode(packet.getMethodArguments()[i]);
                out.writeInt(bytes.length);
                out.writeBytes(bytes);
            }
        } else {
            out.writeInt(0);
        }
    }
}
