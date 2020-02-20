package com.raysonxin.rpc.protocol;

import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @className: ProtocolConstant.java
 * @author: raysonxin
 * @date: 2020/2/11 4:21 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
public class ProtocolConstant {
    public static final int MAGIC_NUMBER = 10086;

    public static final int VERSION = 1;

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
}
