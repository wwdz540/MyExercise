package com.example.demo.lock;

public interface LockSupport {
    boolean tryLock(String resourceId,long expire);
    void releaseLock(String resourceId);
}
