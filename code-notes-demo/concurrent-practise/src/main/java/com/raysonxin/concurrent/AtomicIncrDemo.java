package com.raysonxin.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: AtomicIncrDemo.java
 * @author: raysonxin
 * @date: 2020/2/19 6:16 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class AtomicIncrDemo {

    static int commonInt=0;
    static volatile int volatileInt = 0;
    static AtomicInteger count = new AtomicInteger(0);
    static CountDownLatch downLatch = new CountDownLatch(1);
    static CountDownLatch waitLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        for (int j = 0; j < 10000; j++) {
                            count.incrementAndGet();
                            volatileInt++;
                            commonInt++;
                        }
                        waitLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        downLatch.countDown();

        try {
            waitLatch.await();
            System.out.println(count.get());
            System.out.println(volatileInt);
            System.out.println(commonInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
