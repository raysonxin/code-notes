package com.raysonxin.rpc.exception;

/**
 * @className: MethodMatchException.java
 * @author: raysonxin
 * @date: 2020/2/12 8:31 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class MethodMatchException extends RuntimeException {

    public MethodMatchException(String message) {
        super(message);
    }

    public MethodMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodMatchException(Throwable cause) {
        super(cause);
    }
}
