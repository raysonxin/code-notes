package com.raysonxin.proxy;

/**
 * @className: BookProxy.java
 * @author: raysonxin
 * @date: 2020/2/16 12:10 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class BookProxy implements BookService {

    private BookService bookService;

    public BookProxy(BookService bookService) {
        this.bookService = bookService;
    }

    public String bookTicket(String name) {
        System.out.println("start proxy...");
        String bookResult = bookService.bookTicket(name);
        System.out.println("stop proxy...");
        return bookResult;
    }
}
