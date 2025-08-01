package com.project4test.project4test.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Component
public class RedisUtil {
    private final RedisTemplate<String,String> redisTemplate;

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void set(String key,String value,long expire,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,expire, timeUnit);
    }
    public void delete(String key){
        redisTemplate.delete(key);
    }
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }
}
