package com.raysonxin.collections;

/**
 * @className: PrintTaskOrderDemo.java
 * @author: raysonxin
 * @date: 2020/2/20 12:12 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class PrintTaskOrderDemo {

    public static void main(String[] args) {

    }

    private static void printOrder(int[] input, int len, int[] output) {

        int hasPrintedCount = 0;
        for (hasPrintedCount = 0; hasPrintedCount < len; hasPrintedCount++) {
            int task = input[0];

            for (int i = 1; i < len - hasPrintedCount; i++) {
                if (input[i] > task) {

                }
            }
        }

    }


}
