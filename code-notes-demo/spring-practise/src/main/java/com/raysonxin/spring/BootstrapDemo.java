package com.raysonxin.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @className: BootstrapDemo.java
 * @author: raysonxin
 * @date: 2020/2/7 5:59 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class BootstrapDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("Context Startup");
        MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService.getMessage());

    }
}
