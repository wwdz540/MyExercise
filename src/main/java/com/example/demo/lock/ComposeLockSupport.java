package com.example.demo.lock;

public class ComposeLockSupport implements LockSupport {
    private MapLockSupport mapLockSupport;
    private RedisLockSupport redisLockSupport;

    public ComposeLockSupport(MapLockSupport mapLockSupport, RedisLockSupport redisLockSupport) {
        this.mapLockSupport = mapLockSupport;
        this.redisLockSupport = redisLockSupport;
    }

    @Override
    public boolean tryLock(String resourceId, long expire) {
        if(mapLockSupport.tryLock(resourceId,expire)){
            if(redisLockSupport.tryLock(resourceId,expire)){
               return true;
            }else{
                mapLockSupport.releaseLock(resourceId);
            }
        }
        return false;
    }

    @Override
    public void releaseLock(String resourceId) {
        redisLockSupport.releaseLock(resourceId);
        mapLockSupport.releaseLock(resourceId);
    }
}
