package com.raysonxin.leetcode.question_001_twosum;

/**
 * @className: SimpleWayDemo.java
 * @author: raysonxin
 * @date: 2020/1/15 6:04 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class Program {

    /* *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * 来源：力扣（LeetCode）
     * 链接：https://dev.lingkou.xyz/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    * */


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] results=solution.twoSumHashMap(nums, target);
        if(results!=null){
            for(int i=0;i<results.length;i++){
                System.out.print(results[i]+",");
            }
        }
    }


}
