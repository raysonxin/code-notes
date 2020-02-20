package com.raysonxin.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @className: AtomicIntegerDemo.java
 * @author: raysonxin
 * @date: 2020/1/31 1:01 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class AtomicDemo {

    static AtomicInteger atomicInteger = new AtomicInteger();
    static AtomicIntegerFieldUpdater<User> updater=AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    static AtomicInteger count=new AtomicInteger(0);


    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.addAndGet(10));
        System.out.println(atomicInteger.getAndAdd(1));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(12, 1));
        System.out.println(atomicInteger.get());

        User conan=new User("conan",10);
        System.out.println(updater.getAndIncrement(conan));
        System.out.println(updater.get(conan));
    }
}

class User {
    private String name;
    //若使用AtomicIntegerFieldUpdater进行修改，必须声明为public volatile
    public volatile int old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public User(String name, int old) {
        this.name = name;
        this.old = old;
    }
}
