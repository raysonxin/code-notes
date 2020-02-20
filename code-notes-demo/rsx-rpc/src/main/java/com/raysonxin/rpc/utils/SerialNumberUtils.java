package com.raysonxin.rpc.utils;

import java.util.UUID;

/**
 * @className: SerialNumberUtils.java
 * @author: raysonxin
 * @date: 2020/2/12 10:08 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public enum SerialNumberUtils {
    X;

    public String generateSerialNumber(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
