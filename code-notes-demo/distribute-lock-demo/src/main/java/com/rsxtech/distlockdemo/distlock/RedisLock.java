package com.rsxtech.distlockdemo.distlock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author raysonxin
 * @since 2021/4/9
 */
@Service
public class RedisLock {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 加锁，最多等待maxWait毫秒
     *
     * @param lockKey   锁定key
     * @param lockValue 锁定value
     * @param timeout   锁定时长（毫秒）
     * @param maxWait   加锁等待时间（毫秒）
     * @return true-成功，false-失败
     */
    public boolean tryAcquire(String lockKey, String lockValue, int timeout, long maxWait) {
        long start = System.currentTimeMillis();

        while (true) {
            // 尝试加锁
            Boolean ret = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.MILLISECONDS);
            if (!ObjectUtils.isEmpty(ret) && ret) {
                return true;
            }

            // 计算已经等待的时间
            long now = System.currentTimeMillis();
            if (now - start > maxWait) {
                return false;
            }

            try {
                Thread.sleep(200);
            } catch (Exception ex) {
                return false;
            }
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey   锁定key
     * @param lockValue 锁定value
     * @return true-成功，false-失败
     */
    public boolean releaseLock(String lockKey, String lockValue) {
        // lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.opsForValue().getOperations().execute(redisScript, Collections.singletonList(lockKey), lockValue);
        return result != null && result > 0L;
    }
}