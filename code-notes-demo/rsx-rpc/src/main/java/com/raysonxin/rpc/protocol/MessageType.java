package com.raysonxin.rpc.protocol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @className: MessageType.java
 * @author: raysonxin
 * @date: 2020/2/11 10:46 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@RequiredArgsConstructor
public enum MessageType {

    /**
     * 请求
     */
    REQUEST((byte) 1),

    /**
     * 响应
     */
    RESPONSE((byte) 2),

    /**
     * PING
     */
    PING((byte) 3),

    /**
     * PONG
     */
    PONG((byte) 4),

    /**
     * NULL
     */
    NULL((byte) 5),
    ;

    @Getter
    private final Byte type;

    public static MessageType fromValue(byte value) {
        for (MessageType type : MessageType.values()) {
            if (type.getType() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("value = %s", value));
    }
}
