package com.example.demo;

import ch.qos.logback.core.util.TimeUtil;
import com.example.demo.lock.LockSupport;
import com.example.demo.lock.MapLockSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class LockTest {

    @Test
    public void testMapLock() throws InterruptedException {
        MapLockSupport lockSupport = new MapLockSupport();
        boolean result = lockSupport.tryLock("resouce1",1000);


        result = lockSupport.tryLock("resouce1",1000);


        TimeUnit.SECONDS.sleep(3);
        result = lockSupport.tryLock("resouce1",1000);
        System.out.println(result);

    }

    @Test
    public void test2(){
        RedisConnectionFactory factory = new LettuceConnectionFactory("localhost",6739);
        RedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.opsForValue().set("name","wangzhiping");

    }
}
