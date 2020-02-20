package com.raysonxin.rpc.server;

/**
 * @className: MethodMatcher.java
 * @author: raysonxin
 * @date: 2020/2/12 8:25 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public interface MethodMatcher {

    /**
     * 查找一个匹配度最高的方法信息
     *
     * @param input input
     * @return output
     */
    MethodMatchOutput selectOneBestMatchMethod(MethodMatchInput input);

}
