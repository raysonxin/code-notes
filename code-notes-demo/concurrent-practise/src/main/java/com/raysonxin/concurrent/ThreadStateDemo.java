package com.raysonxin.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @className: ThreadStateDemo.java
 * @author: raysonxin
 * @date: 2020/1/29 10:47 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Current State: " + thread.getState());
        thread.start();
        System.out.println("Current State: " + thread.getState());
        thread.join();
        System.out.println("Current State: " + thread.getState());

        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("abcd");

        System.out.println( Runtime.getRuntime().availableProcessors());
    }
}
