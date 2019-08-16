package com.wxtest.demo.utils;

import com.google.gson.Gson;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liping on 2017/4/27.
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    public List<Map<String, Object>> getInOtherContactHisName(Long userId, String phone) {
        Gson gson = new Gson();
        List<Map<String, Object>> retList = new ArrayList<>();
        List<String> range = redisTemplate.opsForList().range("InotherContactHisname:" + userId + ":" + phone, 0, -1);
        if (range != null && range.size() > 0) {
            logger.info("InotherContactHisname:" + userId + ":" + phone + ":命中缓存....");
            List<Object> list = new ArrayList<>();
            for (String json : range) {
                if (json.trim().equals("\"\"")) {
                    continue; // 判断是否为空避免穿透redis
                }
                list.add(gson.fromJson(json, Object.class));
            }
            List UserPhoneContactss = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(UserPhoneContactss)) {
                for (Object userPhoneContacts : UserPhoneContactss) {
                    redisTemplate.opsForList().rightPush("InotherContactHisname:" + userId + ":" + phone,
                            gson.toJson(userPhoneContacts));
                }
                redisTemplate.expire("InotherContactHisname:" + userId + ":" + phone, 2, TimeUnit.DAYS);
            }
        }
        return retList;
    }

    public void putStringKeyValue(String key, String value) {
        BoundValueOperations<String, String> stringTemplate = redisTemplate.boundValueOps(key);
        stringTemplate.set(value,2, TimeUnit.DAYS);
    }

    public String getValueByKey(String key) {
        BoundValueOperations<String, String> stringTemplate = redisTemplate.boundValueOps(key);
        return stringTemplate.get();
    }

    /**
     * 对传入的key进行加Nx,如果可以设置成功
     */
    public Boolean setNx(String key) {
        Boolean result = redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), key.getBytes());
        if (result) {
            //设置过期时间为 30分钟
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        }
        return result;
    }

    /**
     * 最终加强分布式锁 key键为
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key, long planExpireTime) {
        // 利用lambda表达式执行
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            //当前时间+预设过期时间 作为值
            long expireAt = System.currentTimeMillis() + planExpireTime;
            //设置键值对,setNx如果已经存在该键值对,那么设置setNx会失败,该键值在程序中处理完成则del掉
            Boolean acquire = connection.setNX(key.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return Boolean.TRUE;
            } else {
                connection.get(key.getBytes());
                String value = new String(connection.get(key.getBytes()));
                if (StringUtils.isNotBlank(value)) {
                    long expireTime = Long.parseLong(value);
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(key.getBytes(),
                                String.valueOf(System.currentTimeMillis() + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return Boolean.FALSE;
        });
    }

    /**
     * 删除锁
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}

class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
