package com.raysonxin.rpc.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @className: RequestMessagePacket.java
 * @author: raysonxin
 * @date: 2020/2/11 3:46 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestMessagePacket extends BaseMessagePacket {

    /**
     * 接口全类名
     */
    private String interfaceName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法参数签名
     */
    private String[] methodArgumentSignatures;

    /**
     * 方法参数
     */
    private Object[] methodArguments;
}
