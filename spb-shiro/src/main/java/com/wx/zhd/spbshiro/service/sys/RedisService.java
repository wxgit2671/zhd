package com.wx.zhd.spbshiro.service.sys;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger("redisLogger");
    public static String REDIS_KEY_MANAGER_ALERT_COUNT = "managerAlertService:count:";
    /**
     * redis
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 设置token三天过期
     *
     * @return void 返回类型
     * @Title: setToken
     * @Description: 存入令牌
     */
    public void setToken(String key) {
        redisTemplate.opsForValue().set(key, getToken(), 3L, TimeUnit.DAYS);
    }

    /**
     * 缓存数据
     */
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(String key, String value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * 缓存数据，如果存在该key，则不会缓存数据并返回false，如果不存在该key，则缓存数据且返回true
     *
     * @return 如果已经存在该key则返回false, 否则返回true
     */
    public boolean setValueIfAbsent(String key, String value, Long time, TimeUnit timeUnit) {
        boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (result) {
            this.expire(key, time, timeUnit);
        }
        return result;
    }

    /**
     * @param @param key
     * @return User 返回类型
     * @Title: get
     * @Description:
     */
    public String get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : value.toString();
    }

    /**
     * @return void 返回类型
     * @Title: remove
     * @Description:
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @return void 返回类型
     * @Title: remove
     * @Description:
     */
    public void remove(List<String> keys) {
        logger.info("移除key{}", new Gson().toJson(keys));
        redisTemplate.delete(keys);
    }

    /**
     * 获取所有key
     *
     * @author: haidong
     * @date: 2016年2月16日 下午2:34:04
     */
    public List<String> keys() {
        return keys("*");
    }

    /**
     * 根据表达式获取key
     *
     * @date: 2016年2月16日 下午2:34:04
     */
    public List<String> keys(String pattern) {
        return keys(pattern, null);
    }

    /**
     * 根据表达式获取key，并排序
     *
     * @date: 2016年2月16日 下午2:34:04
     */
    public List<String> keys(String pattern, Comparator<String> comparator) {
        List<String> keys = Lists.newArrayList(redisTemplate.keys(pattern).iterator());
        if (comparator != null) {
            keys.sort(comparator);
        }
        return keys;

    }

    /**
     * 鉴权，是否登录，token是否有效
     */
    public boolean authentication(String key, String token) {
        try {
            return token.equals(get(key));
        } catch (Exception e) {
            logger.error("验证token失败key:" + key + " token：" + token, e);
            return false;
        }
    }

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将values放到key下
     *
     * @date: 2016年3月29日 上午9:56:18
     */
    public void setAsList(String key, List<String> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 讲一个value放入key下的list中
     *
     * @date: 2016年3月29日 上午10:26:45
     */
    public void setAsList(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 获取一个key中第index的元素
     *
     * @date: 2016年3月29日 上午10:27:06
     */
    public String getAsList(String key, int index) {
        return redisTemplate.opsForList().index(key, index);

    }

    /**
     * 获取一个key中第index的元素
     *
     * @date: 2016年3月29日 上午10:27:06
     */
    public Long countAsList(String key) {
        return redisTemplate.opsForList().size(key);

    }

    /**
     * 获取一个key的list
     *
     * @date: 2016年3月29日 上午10:27:06
     */
    public List<String> getAsList(String key) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        long size = ops.size(key);
        return getAsList(key, 0, size);

    }

    /**
     * 获取list集合index位置的元素
     */
    public String getFromList(String key, int index) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        return ops.index(key, index);
    }


    /**
     * 获取一个key的从begin到end长度的list
     *
     * @date: 2016年3月29日 上午10:27:53
     */
    public List<String> getAsList(String key, long begin, long end) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        return ops.range(key, begin, end);
    }

    /**
     * 删除key中的value
     *
     * @date: 2016年3月29日 上午10:28:09
     */
    public void removeFromList(String key, String value) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        Long num = ops.remove(key, 0, value);
        logger.info("{}删除个数为{}", value, num);
    }

    /**
     * 放入hash
     */
    public void setAsHash(String key, Object hashKey, Object hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    /**
     * 从hash中移除
     */
    public void removeFromHash(String key, Object hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 获取整个hash
     */
    public Map<Object, Object> getAsHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 从hash中获取hashKey的值
     */
    public Object getFromHash(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取hash的大小
     */
    public Long getHashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * @description 根据key删除
     */
    public void deleteKey(String key) {
        this.redisTemplate.delete(key);
        logger.info("删除{} redis cache", key);
    }

    /**
     * 获取key的过期时间(秒)
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 为key设置过期时间
     */
    public void expire(String key, Long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 将Map放入hash
     */
    public void setMap(String key, Map<Object, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * @date 2018/4/4 10:23
     */
    public Double getHaiXiangAmount(String key, Double value) {
        return redisTemplate.opsForValue().increment(key, value);
    }
}
