package com.raysonxin.leetcode.linklist;

/**
 * @className: ReverseLinkListDemo.java
 * @author: raysonxin
 * @date: 2020/2/14 11:33 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class ReverseLinkListDemo {
    public static void main(String[] args) {
        Node head = new Node(0);

        //build
        Node prev = head;
        for (int i = 1; i < 10; i++) {
            Node node = new Node(i);
            prev.next = node;
            prev = node;
        }

        System.out.println(head.next.data);
        //reverse
        output(head.next);
    }

    private static void output(Node node) {
        if (node.next == null) {
            System.out.print(node.data + " ");
            return;
        }

        output(node.next);
        System.out.print(node.data + " ");
    }

    static class Node {
        public Node(int data) {
            this.data = data;
            next = null;
        }

        int data;
        Node next;
    }
}
