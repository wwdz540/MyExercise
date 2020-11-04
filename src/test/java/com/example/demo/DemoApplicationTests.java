package com.example.demo;

import com.example.demo.lock.ComposeLockSupport;
import com.example.demo.lock.MapLockSupport;
import com.example.demo.lock.RedisLockSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
	}


	@Test
	public void testSetValue(){
		RedisLockSupport redisLockSupport = new RedisLockSupport(redisTemplate);
		MapLockSupport mapLockSupport = new MapLockSupport();

		ComposeLockSupport lockSupport = new ComposeLockSupport(mapLockSupport,redisLockSupport);

		boolean result = lockSupport.tryLock("sourceId:1", 1000);

		System.out.println(result);
		result = lockSupport.tryLock("sourceId:1", 1000);
		System.out.println(result);

		lockSupport.releaseLock("sourceId:1");

		result = lockSupport.tryLock("sourceId:1", 1000);
		System.out.println(result);

	}


}
