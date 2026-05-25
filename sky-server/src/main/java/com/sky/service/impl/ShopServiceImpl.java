package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void updateStatus(Integer status) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("Shop-status",status);
    }

    @Override
    public Integer gainStatus() {
        return (Integer) redisTemplate.opsForValue().get("Shop-status");
    }
}
