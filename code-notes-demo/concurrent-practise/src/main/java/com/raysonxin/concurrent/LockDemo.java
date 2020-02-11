package com.raysonxin.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: LockDemo.java
 * @author: raysonxin
 * @date: 2020/1/31 12:32 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class LockDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    static int cnt = 0;
    static Map<Integer, Thread> threadMap = new HashMap<Integer, Thread>();


    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                int n = 100000;
                while (n > 0) {
                    cnt++;
                    n--;
                }
                reentrantLock.unlock();
                countDownLatch.countDown();
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            threadMap.put(i, thread);
            thread.start();
        }
        try {
            countDownLatch.await();
            System.out.println("cnt=" + cnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

final class Node {
    /**
     * 节点状态，取值范围是：1：已取消（取消排队），0：默认值，-1：排队中需要唤醒，-2：条件等待中，-3：退出条件等待，向后传播
     * */
    volatile int waitStatus;

    /**
     * 前驱节点的引用
     * */
    volatile Node prev;

    /**
     * 后继节点的引用
     * */
    volatile Node next;

    /**
     * 线程对象
     * */
    volatile Thread thread;

    Node nextWaiter;
}
