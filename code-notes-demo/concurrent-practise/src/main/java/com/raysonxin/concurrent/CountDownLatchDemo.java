package com.raysonxin.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @className: CountDownLatchDemo.java
 * @author: raysonxin
 * @date: 2020/1/29 11:55 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Player(begin, end), String.valueOf(i));
            thread.start();
        }

        try {
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Player implements Runnable {

    private CountDownLatch begin;
    private CountDownLatch end;
    private Random random = new Random();

    Player(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start !");
            begin.await();
            int v = random.nextInt(10);
            Thread.sleep(v * 1000);
            System.out.println(Thread.currentThread().getName() + " arrived !");
            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}