package com.raysonxin.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @className: SemaphoreDemo.java
 * @author: raysonxin
 * @date: 2020/1/30 4:11 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(4, true);
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
            executorService.submit(new Customer(i + 1, semaphore));
        }

        Thread monitor = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    int waitCount = semaphore.getQueueLength();
                    System.out.println("当前排队人数：" + waitCount);
                    if (waitCount == 0) {
                        break;
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        monitor.start();

        executorService.shutdown();
    }
}

class Customer implements Runnable {

    private int custId;
    private Semaphore semaphore;
    private Random random = new Random();

    public Customer(int custId, Semaphore semaphore) {
        this.custId = custId;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.custId + "开始办理业务...");
            Thread.sleep(random.nextInt(10) * 1000);
            System.out.println(this.custId + "业务办理完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

