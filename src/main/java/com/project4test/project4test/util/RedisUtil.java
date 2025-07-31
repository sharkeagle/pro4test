package com.project4test.project4test.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存数据
     * @param key 缓存键
     * @param value 缓存值
     * @param timeout 缓存时间
     * @param timeUnit 缓存时间单位
     */
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取缓存数据
     * @param key 缓存键
     * @return 缓存值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存数据
     * @param key 缓存键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}
