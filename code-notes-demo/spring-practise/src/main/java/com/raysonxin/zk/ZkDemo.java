package com.raysonxin.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: ZkDemo.java
 * @author: raysonxin
 * @date: 2020/2/17 5:25 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@SpringBootApplication
public class ZkDemo {

    @Autowired
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        SpringApplication.run(ZkDemo.class, args);

        try {
            zooKeeper.create("/zkdemo","true".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
