package com.raysonxin.rpc.server;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @className: MethodMatchOutput.java
 * @author: raysonxin
 * @date: 2020/2/12 11:14 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
public class MethodMatchOutput {
    /**
     * 目标方法实例
     */
    private Method targetMethod;

    /**
     * 目标实现类，这个可能是被cglib增强过的类型，是宿主类的子类；如果没有被cglib增强过，那么就是宿主类
     */
    private Class<?> targetClass;

    /**
     * 宿主类
     */
    private Class<?> targetUserClass;

    /**
     * 宿主类Bean实例
     */
    private Object target;

    /**
     * 方法参数类型列表
     */
    private List<Class<?>> parameterTypes;

}
