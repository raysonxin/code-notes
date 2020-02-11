package com.raysonxin.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @className: ExchangerDemo.java
 * @author: raysonxin
 * @date: 2020/1/30 8:40 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ExchangerDemo {

    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
        Thread producerThread = new Thread(new Producer(buffer1, exchanger));
        Thread consumerThread = new Thread(new Consumer(buffer2, exchanger));
        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {

    private List<String> buffer;
    private Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("生产者第" + i + "次提供");
            for (int j = 1; j <= 3; j++) {
                System.out.println("生产者装入" + i + "--" + j);
                buffer.add("data:" + i + "--" + j);
            }
            System.out.println("生产者装满，等待与消费者交换");
            try {
                exchanger.exchange(buffer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                buffer = exchanger.exchange(buffer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("消费者第" + i + "次提取数据");
            for (int j = 1; j <= 3; j++) {
                System.out.println("消费者：" + buffer.get(0));
                buffer.remove(0);
            }
        }
    }
}