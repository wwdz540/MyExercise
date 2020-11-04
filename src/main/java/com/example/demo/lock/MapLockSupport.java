package com.example.demo.lock;

import java.util.concurrent.ConcurrentHashMap;


public class MapLockSupport implements LockSupport {
    private ConcurrentHashMap<String,LockInfo> locksMap = new ConcurrentHashMap<>();

    public MapLockSupport() {
    }

    @Override
    public boolean tryLock(String resourceId,final long expire) {

        LockInfo lockInfo ;
        do {
           lockInfo = locksMap.putIfAbsent(resourceId, new LockInfo(resourceId, expire)); //返回null代表添加成功
           if (lockInfo == null) return true;
           try {
               lockInfo = locksMap.computeIfPresent(resourceId, (k, v) -> { // 判断是否超时
                   if (v.getExpireTimeStamp() < System.currentTimeMillis()) {
                       v.setExpire(expire);
                   } else {
                       throw new TimeoutException();
                   }
                   return v;
               });
           }catch (TimeoutException ex){
               return false;
           }
       }while(lockInfo == null);
        return true;
    }




    @Override
    public void releaseLock(String resouceId) {
        locksMap.remove(resouceId);
    }


    public static class TimeoutException extends RuntimeException{

    }

}
