package com.example.demo.lock;


public class LockInfo  {
    private final String resouceId;
    private volatile   Long expireTimeStamp;

    public LockInfo(String resouceId, Long expire) {
        this.resouceId = resouceId;
        this.expireTimeStamp = System.currentTimeMillis() + expire;
    }

    public String getResouceId() {
        return resouceId;
    }

    public void setExpire(Long expire) {
        this.expireTimeStamp = System.currentTimeMillis() + expire;
        System.out.println("======="+expireTimeStamp);
    }

    public Long getExpireTimeStamp() {
        return expireTimeStamp;
    }




}
