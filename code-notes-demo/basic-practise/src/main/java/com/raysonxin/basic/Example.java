package com.raysonxin.basic;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

/**
 * @className: Example.java
 * @author: raysonxin
 * @date: 2020/2/4 5:44 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class Example {
    //HashSet

    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};

    public static void main(String args[]) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);

        System.out.println();
        String str1 = "hello";
        String str2 = "he" + "llo";
        String str3 = "he" + new String("llo");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);

        System.out.println(getValue(2));
        Child child=new Child("mike");

    }

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }

    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
}

class People {
    String name;
    public People() {
        System.out.print(1);
    }
    public People(String name) {
        System.out.print(2);
        this.name = name;
    }
}
class Child extends People {
    People father;
    public Child(String name) {
        System.out.print(3);
        this.name = name;
        father = new People(name + ":F");
    }
    public Child() {
        System.out.print(4);
    }
}