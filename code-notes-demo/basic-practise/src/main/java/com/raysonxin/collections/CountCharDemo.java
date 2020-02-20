package com.raysonxin.collections;

import java.util.Scanner;

public class CountCharDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        char targetChar = scanner.nextLine().toLowerCase().charAt(0);

        int len = input.length();
        int index = 0;
        int count = 0;
        while (index < len) {
            if (targetChar == input.charAt(index)) {
                count++;
            }
            index++;
        }
        System.out.println(count);
    }
}
