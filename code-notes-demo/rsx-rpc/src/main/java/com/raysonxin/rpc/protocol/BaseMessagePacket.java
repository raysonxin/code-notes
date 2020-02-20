package com.raysonxin.rpc.protocol;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: BaseMessagePacket.java
 * @author: raysonxin
 * @date: 2020/2/11 12:00 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
public class BaseMessagePacket implements Serializable {
    /**
     * 魔数
     */
    private int magicNumber;

    /**
     * 版本号
     */
    private int version;

    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 消息类型
     */
    private MessageType messageType;

    /**
     * 附件 - K-V形式
     */
    private Map<String, String> attachments = new HashMap<>();

    /**
     * 添加附件
     */
    public void addAttachment(String key, String value) {
        attachments.put(key, value);
    }

    public void encode(ByteBuf out) {
        out.writeInt(getMagicNumber());
        out.writeInt(getVersion());
        out.writeInt(getSerialNumber().length());
        out.writeCharSequence(getSerialNumber(), ProtocolConstant.UTF_8);
        out.writeByte(getMessageType().getType());
        Map<String, String> attachments = getAttachments();
        out.writeInt(attachments.size());
        attachments.forEach((k, v) -> {
            out.writeInt(k.length());
            out.writeCharSequence(k, ProtocolConstant.UTF_8);
            out.writeInt(v.length());
            out.writeCharSequence(v, ProtocolConstant.UTF_8);
        });
    }

    public void decode(ByteBuf in) {
        setMagicNumber(in.readInt());
        setVersion(in.readInt());
        int serialNumberLength = in.readInt();
        setSerialNumber(in.readCharSequence(serialNumberLength, ProtocolConstant.UTF_8).toString());
        byte messageTypeByte = in.readByte();
        setMessageType(messageType.fromValue(messageTypeByte));
        Map<String, String> attachments = Maps.newHashMap();
        int attachmentSize = in.readInt();
        if (attachmentSize > 0) {
            for (int i = 0; i < attachmentSize; i++) {
                int keyLength = in.readInt();
                String key = in.readCharSequence(keyLength, ProtocolConstant.UTF_8).toString();
                int valueLength = in.readInt();
                String value = in.readCharSequence(valueLength, ProtocolConstant.UTF_8).toString();
                attachments.put(key, value);
            }
        }
    }
}
