package com.raysonxin.rpc.server;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @className: ArgumentConvertInput.java
 * @author: raysonxin
 * @date: 2020/2/15 5:52 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
public class ArgumentConvertInput {

    private Method method;

    private List<Class<?>>  parameterTypes;

    private List<Object> arguments;

}
