package com.raysonxin.rpc.server;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @className: MethodMatchInput.java
 * @author: raysonxin
 * @date: 2020/2/12 11:07 上午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@EqualsAndHashCode
@Data
public class MethodMatchInput {

    private String interfaceName;

    private String methodName;

    private List<String> methodArgumentSignatures;

    private int methodArgumentArraySize;
}
