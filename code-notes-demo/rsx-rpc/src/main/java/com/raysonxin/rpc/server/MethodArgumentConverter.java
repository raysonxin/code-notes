package com.raysonxin.rpc.server;

/**
 * @className: MethodArgumentConverter.java
 * @author: raysonxin
 * @date: 2020/2/15 5:51 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public interface MethodArgumentConverter {

    ArgumentConvertOutput convert(ArgumentConvertInput input);

}
