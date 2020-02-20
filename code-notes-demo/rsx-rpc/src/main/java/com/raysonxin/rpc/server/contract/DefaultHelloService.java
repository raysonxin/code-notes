package com.raysonxin.rpc.server.contract;

import com.raysonxin.rpc.contract.HelloService;
import org.springframework.stereotype.Service;

/**
 * @className: DefaultHelloService.java
 * @author: raysonxin
 * @date: 2020/2/15 8:27 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Service
public class DefaultHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return String.format("%s say hello!", name);
    }
}
