package com.rsxtech.distlockdemo;

import com.rsxtech.distlockdemo.distlock.RedisLock;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DistributeLockDemoApplicationTests {


    @Resource
    private RedisLock redisLock;

    @Test
    public void testLock() {
        redisLock.tryAcquire("abcd", "abcd", 5 * 60 * 1000, 5 * 1000);
        redisLock.releaseLock("abcd", "abcd");


    }

    @Test
    public void testRedLock() throws InterruptedException {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        final RedissonClient client = Redisson.create(config);

        // 获取锁实例
        final RLock lock = client.getLock("test-lock");

        // 加锁
        lock.lock(60 * 1000, TimeUnit.MILLISECONDS);
        try {
            // 假装做些什么事情
            Thread.sleep(50 * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

}
