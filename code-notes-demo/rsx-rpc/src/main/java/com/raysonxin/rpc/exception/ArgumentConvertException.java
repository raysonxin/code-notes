package com.raysonxin.rpc.exception;

/**
 * @className: ArgumentConvertException.java
 * @author: raysonxin
 * @date: 2020/2/15 5:58 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ArgumentConvertException extends RuntimeException {

    public ArgumentConvertException(String message){
        super(message);
    }

    public ArgumentConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentConvertException(Throwable cause) {
        super(cause);
    }
}
