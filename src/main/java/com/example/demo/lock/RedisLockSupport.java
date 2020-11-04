package com.example.demo.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RedisLockSupport implements  LockSupport {

    private RedisTemplate redisTemplate ;


    public RedisLockSupport(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean tryLock(String resourceId, long expire) {
        if(redisTemplate.opsForValue().setIfAbsent(resourceId, expire, Duration.ofMillis(expire))){
            return true;
        }
       return false;
    }

    @Override
    public void releaseLock(String resourceId) {
        redisTemplate.delete(resourceId);
    }
}
