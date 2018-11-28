package com.szxs.spring;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String getCity() {
        //通过模板,获取到String类型的redis对象
        ValueOperations<String, String> redisString = redisTemplate.opsForValue();
        //使用set方法,保存key和value的值
        redisString.set("city", "aabbcc");

        //使用get(key)的方法获取到city对应的值
        String string = redisString.get("city");
        return string;
    }

    public List<String> getCitys() {
        //通过模板获取list类型的redis
        ListOperations<String, String> redisList = redisTemplate.opsForList();

        //通过key依次插入数据
        redisList.leftPush("city9", "南京");
        redisList.leftPush("city9", "上海");
        redisList.leftPush("city9", "北京");
        redisList.leftPush("city9", "上海");
        redisList.leftPush("city9", "南京");

        //查找索引范围内的所有数据
        List<String> range = redisList.range("city9", 0, 10);
        return range;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
