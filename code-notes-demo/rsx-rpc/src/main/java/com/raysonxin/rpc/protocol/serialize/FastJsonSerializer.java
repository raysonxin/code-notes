package com.raysonxin.rpc.protocol.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @className: FastJsonSerializer.java
 * @author: raysonxin
 * @date: 2020/2/11 4:08 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public enum FastJsonSerializer implements Serializer {

    X;

    @Override
    public byte[] encode(Object target) {
        return JSON.toJSONBytes(target);
    }

    @Override
    public Object decode(byte[] bytes, Class<?> targetClass) {
        return JSON.parseObject(bytes, targetClass);
    }
}
