package com.raysonxin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @className: ProxyDemo.java
 * @author: raysonxin
 * @date: 2020/2/16 4:12 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ProxyDemo {

    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        BookProxy proxy = new BookProxy(bookService);
        proxy.bookTicket("raysonxin");

        ClassLoader classLoader = bookService.getClass().getClassLoader();
        Class[] interfaces = bookService.getClass().getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandlerImpl(bookService);
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        BookService service = (BookService) o;
        service.bookTicket("sdfsd");
    }

}
