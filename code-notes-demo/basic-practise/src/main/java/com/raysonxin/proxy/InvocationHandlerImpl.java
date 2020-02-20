package com.raysonxin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @className: InvocationHandlerImpl.java
 * @author: raysonxin
 * @date: 2020/2/16 4:23 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class InvocationHandlerImpl implements InvocationHandler {

    BookService target;

    public InvocationHandlerImpl(BookService bookService) {
        this.target = bookService;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("a");
        Object obj = method.invoke(target, args);
        System.out.println("b");
        return obj;
    }
}
