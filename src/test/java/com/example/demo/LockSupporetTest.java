package com.example.demo;


import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;


public class LockSupporetTest {

    @Test
    public void test1(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap();
        Object o = map.putIfAbsent("k1", "k1");

        System.out.println(o);
        System.out.println( map.putIfAbsent("k1", "k2"));

        System.out.println("=="+map.get("k1"));


       String a= map.computeIfPresent("k2",(k,v)->{
           return v+"v2";
        });
        System.out.println(a);

    }
}
