package com.raysonxin.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @className: VolatileAddDemo.java
 * @author: raysonxin
 * @date: 2020/2/13 12:11 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class VolatileAddDemo {

    private static volatile int number = 0;
    static CountDownLatch downLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        number++;
                    }
                    downLatch.countDown();
                }
            });
            thread.start();
        }

        downLatch.await();
        System.out.println(number);
    }
}

