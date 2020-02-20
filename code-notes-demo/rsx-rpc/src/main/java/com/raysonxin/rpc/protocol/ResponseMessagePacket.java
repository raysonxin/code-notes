package com.raysonxin.rpc.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @className: ResponseMessagePacket.java
 * @author: raysonxin
 * @date: 2020/2/11 3:48 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseMessagePacket extends BaseMessagePacket {

    /**
     * error code
     * */
    private Long errorCode;

    /**
     * 消息描述
     * */
    private String message;

    /**
     * 消息载菏
     * */
    private Object payload;

}
