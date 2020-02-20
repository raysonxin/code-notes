package com.raysonxin.proxy;

/**
 * @className: BookServiceImpl.java
 * @author: raysonxin
 * @date: 2020/2/16 12:10 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class BookServiceImpl implements BookService {
    public String bookTicket(String name) {
        System.out.println("execute book");
        return String.format("%s book ticket!", name);
    }
}
