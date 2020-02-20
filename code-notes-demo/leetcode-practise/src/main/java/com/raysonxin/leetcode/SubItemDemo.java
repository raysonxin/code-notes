package com.raysonxin.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * ByteDance面试题
 * 给定m个不重复的字符 [a, b, c, d]，以及一个长度为n的字符串tbcacbdata，
 * 问能否在这个字符串中找到一个长度为m的连续子串，使得这个子串刚好由上面m个字符组成，
 * 顺序无所谓，返回任意满足条件的一个子串的起始位置，
 * 未找到返回-1。比如上面这个例子，acbd，3
 * */
public class SubItemDemo {
    public static void main(String[] args) {
        String target = "abcd";
        String input = "tbcaacbdata";

        int length = input.length();
        int index = 0;
        int pos = -1;
        List<String> tempList = getTargetList(target);
        while (index < length) {
            String ch = String.valueOf(input.charAt(index));
            if (tempList.contains(ch)) {
                tempList.remove(ch);
                pos = pos == -1 ? index : pos;
                if (tempList.size() == 0) {
                    break;
                }
            } else {
                tempList = getTargetList(target);
                if (pos != -1)
                    index = pos;
                pos = -1;
            }
            index++;
        }
        if (tempList.isEmpty()) {
            System.out.println(pos);
        } else {
            System.out.println(-1);
        }
    }

    private static List<String> getTargetList(String target) {
        // m个不重复字符
        List<String> targetList = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            targetList.add(String.valueOf(target.charAt(i)));
        }
        return targetList;
    }
}
