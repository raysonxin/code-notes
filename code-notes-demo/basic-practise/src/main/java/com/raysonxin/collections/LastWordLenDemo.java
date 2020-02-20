package com.raysonxin.collections;

import java.util.Scanner;

public class LastWordLenDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int len = input.length();
        int ptr = len - 1;
        char ch;

        int newCnt = 0;
        while (ptr >= 0) {
            ch = input.charAt(ptr);
            if (ch != ' ') {
                newCnt++;
            } else if (ch == ' ' && newCnt > 0) {
                break;
            }
            ptr--;
        }

        System.out.println(newCnt);
    }
}
