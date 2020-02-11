package com.raysonxin.basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: Factory.java
 * @author: raysonxin
 * @date: 2020/2/4 5:55 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class FactoryDemo {
    private static final int CAPACITY = 200;
    private static ArrayBlockingQueue<Integer> repository = new ArrayBlockingQueue<Integer>(CAPACITY);
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

    static class Producer implements Runnable {
        AtomicInteger seqFactory = new AtomicInteger();

        public void produce(int product) {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    repository.offer(seqFactory.getAndIncrement());
                }
            } finally {
                lock.unlock();
            }
        }

        public void run() {
            try {
                for (; ; ) {

                    Thread.sleep(10 * 1000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class Consumer {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        public void consume() {
            lock.lock();
            try {
                if (repository.size() > 0) {
                    System.out.println(repository.poll());
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

