package com.raysonxin.rpc.protocol.serialize;

/**
 * @className: Serializer.java
 * @author: raysonxin
 * @date: 2020/2/11 4:06 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public interface Serializer {

    byte[] encode(Object target);

    Object decode(byte[] bytes,Class<?> targetClass);

}
