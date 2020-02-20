package com.raysonxin.rpc.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * @className: ByteBufferUtils.java
 * @author: raysonxin
 * @date: 2020/2/11 8:25 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public enum  ByteBufferUtils {
    X;

    public void encodeUtf8CharSequence(ByteBuf byteBuf,CharSequence charSequence){
        int writerIndex=byteBuf.writerIndex();
        byteBuf.writeInt(0);
        int length= ByteBufUtil.writeUtf8(byteBuf,charSequence);
        byteBuf.setInt(writerIndex,length);
    }

    public byte[] readBytes(ByteBuf byteBuf) {
        int len = byteBuf.readableBytes();
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);
        byteBuf.release();
        return bytes;
    }
}
