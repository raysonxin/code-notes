package com.raysonxin.concurrent;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.util.concurrent.*;

/**
 * @className: ThreadCreateDemo.java
 * @author: raysonxin
 * @date: 2020/1/29 10:17 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ThreadCreateDemo {

    public static void main(String[] args) throws Exception {
        Thread thread = new MyThread();
        thread.start();

        Runnable runnable = new MyRunnable();
        thread = new Thread(runnable);
        thread.start();

        Callable<String> callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(callable);
        System.out.println(future.get());

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        executorService.shutdown();

        ExecutorService es2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            es2.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        es2.shutdown();
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ",this is a thread: MyThread extends Thread");
    }
}

class MyRunnable implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName() + ",this is a thread: MyRunnable implements Runnable");
    }
}

class MyCallable implements Callable<String> {

    public String call() throws Exception {
        return Thread.currentThread().getName() + ",this is a thread: MyCallable implements Callable<T>";
    }
}