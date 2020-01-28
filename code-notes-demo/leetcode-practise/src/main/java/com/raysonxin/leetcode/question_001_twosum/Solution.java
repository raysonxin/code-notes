package com.raysonxin.leetcode.question_001_twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Solution.java
 * @author: raysonxin
 * @date: 2020/1/15 6:43 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class Solution {

    public int[] twoSumSimple(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer second = target - nums[i];
            if (map.containsKey(second)) {
                return new int[]{map.get(second), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
