package com.raysonxin.zk;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @className: ZkConfig.java
 * @author: raysonxin
 * @date: 2020/2/17 5:16 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Configuration
public class ZkConfig {

    @Value("${zookeeper.address}")
    private String zkServers;

    @Bean
    public ZooKeeper zooKeeper() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(zkServers, 5000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
