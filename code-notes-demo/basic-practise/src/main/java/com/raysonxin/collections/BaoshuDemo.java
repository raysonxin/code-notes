package com.raysonxin.collections;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @className: BaoshuDemo.java
 * @author: raysonxin
 * @date: 2020/2/19 10:58 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class BaoshuDemo {

    public static void main(String[] args) {


       /* calculate(4, 3, 1);
        calculate(4, 3, 2);
        calculate(4, 3, 3);
        calculate(4, 3, 4);*/

        for (int i = 1; i < 20; i++) {
            System.out.println(i + " " + getValue(i, 4));
        }

    }

    private static int getValue(int index, int n) {
        int mod = index % (2 * n -2);
        return mod <= n ? mod  : 8 - mod;
    }

    private static void calculate(int n, int m, int k) {
        //构造双向链表
        //头节点
        Node head = null;
        //辅助节点
        Node cur = null;
        for (int i = 1; i <= n; i++) {
            Node node = new Node(i);
            if (head == null) {
                head = node;
            }

            if (cur == null) {
                cur = node;
            } else {
                cur.next = node;
                node.previous = cur;
                cur = node;
            }
        }

        cur = head;
        //从左到右true，否则false
        boolean lr = true;

        //总数
        int counter = 0;
        //满足条件次数
        int times = 0;

        while (times < k) {
            counter++;

            if (isSevenRelated(counter)) {
                if (cur.number == m) {
                    times++;
                }
            }

            //到头了就要掉头
            if ((lr && cur.next == null) || (!lr && cur.previous == null)) {
                lr = !lr;
            }

            //取出下一个
            cur = lr ? cur.next : cur.previous;
        }
        //输出结果
        System.out.println(counter);
    }

    private static boolean isSevenRelated(int number) {
        if (number % 7 == 0 || String.valueOf(number).contains("7")) {
            return true;
        }
        return false;
    }

    static class Node {
        int number;
        Node next;
        Node previous;

        public Node(int number) {
            this.number = number;
            this.next = null;
            this.previous = null;
        }
    }
}
