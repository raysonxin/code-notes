package com.raysonxin.rpc.server;

import lombok.Data;

/**
 * @className: HostClassMethodInfo.java
 * @author: raysonxin
 * @date: 2020/2/12 8:33 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Data
public class HostClassMethodInfo {

    private Class<?> hostClass;

    private Class<?> hostUserClass;

    private Object hostTarget;

}
