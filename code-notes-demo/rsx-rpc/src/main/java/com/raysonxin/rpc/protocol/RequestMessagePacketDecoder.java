package com.raysonxin.rpc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @className: RequestMessagePacketDecoder.java
 * @author: raysonxin
 * @date: 2020/2/11 6:05 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@RequiredArgsConstructor
public class RequestMessagePacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> list) throws Exception {
        RequestMessagePacket packet=new RequestMessagePacket();
        packet.decode(in);

        int interfaceNameLength=in.readInt();
        packet.setInterfaceName(in.readCharSequence(interfaceNameLength,ProtocolConstant.UTF_8).toString());

        int methodNameLength=in.readInt();
        packet.setMethodName(in.readCharSequence(methodNameLength,ProtocolConstant.UTF_8).toString());

        int methodArgumentSignatureArrayLength=in.readInt();
        if(methodArgumentSignatureArrayLength>0){
            String[] methodArgumentSignatures=new String[methodArgumentSignatureArrayLength];
            for(int i=0;i<methodArgumentSignatureArrayLength;i++){
                int methodArgumentSignatureLength=in.readInt();
                methodArgumentSignatures[i]=in.readCharSequence(methodArgumentSignatureLength,ProtocolConstant.UTF_8).toString();
            }
            packet.setMethodArgumentSignatures(methodArgumentSignatures);
        }

        int methodArgumentArrayLength=in.readInt();
        if(methodArgumentArrayLength>0){
            Object[] methodArguments=new Object[methodArgumentArrayLength];
            for(int i=0;i<methodArgumentArrayLength;i++){
                int byteLength=in.readInt();
                methodArguments[i]=in.readBytes(byteLength);
            }
            packet.setMethodArguments(methodArguments);
        }
        list.add(packet);
    }
}
