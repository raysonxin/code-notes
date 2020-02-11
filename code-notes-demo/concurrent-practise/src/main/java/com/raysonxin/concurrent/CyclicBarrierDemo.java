package com.raysonxin.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @className: CyclicBarrierDemo.java
 * @author: raysonxin
 * @date: 2020/1/30 11:40 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {

        Map<Integer, Thread> threadMap = new HashMap<Integer, Thread>();
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("发令枪响了，跑！");
            }
        });

        for (int i = 0; i < 5; i++) {
            Thread t = new CyclicBarrierThread(barrier, "运动员" + i + "号");
            threadMap.put(i, t);
            t.start();
        }
        Thread.sleep(3000);
        threadMap.get(1).interrupt();
        //barrier.reset();
    }
}

class CyclicBarrierThread extends Thread {

    private CyclicBarrier cyclicBarrier;
    private String name;
    private Random random = new Random();

    CyclicBarrierThread(CyclicBarrier barrier, String name) {
        super();
        this.cyclicBarrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "开始准备");
        try {
            Thread.sleep(random.nextInt(10) * 1000);
            System.out.println(name + "准备完毕！等待发令枪...");
            try {
                cyclicBarrier.await();
               // cyclicBarrier.await(random.nextInt(3), TimeUnit.SECONDS);
                System.out.println(name + " 跑了！");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println(name + "看不见起跑线了");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}