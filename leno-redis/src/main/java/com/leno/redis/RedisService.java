package com.leno.redis;

import com.alibaba.fastjson.JSON;
import com.leno.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>redis相关服务</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("unchecked")
    public <K, V> void putHashAll(String key, Map<K, V> vMap) {
        if (vMap == null || vMap.size() == 0) {
            return;
        }
        HashMap<String, String> map = new HashMap<>(vMap.size());
        for (Map.Entry<K, V> item : vMap.entrySet()) {
            map.put(JSON.toJSONString(item.getKey()), JSON.toJSONString(item.getValue()));
        }
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @SuppressWarnings("unchecked")
    public <K, V> void putHash(String key, K hk, V hv) {
        stringRedisTemplate.opsForHash().put(key, JSON.toJSONString(hk), JSON.toJSONString(hv));
    }

    @SuppressWarnings("unchecked")
    public <K, V> V getHashValue(String key, K hk, Class<V> vClass) {
        String value = StringUtil.valueOf(stringRedisTemplate.opsForHash().get(key, JSON.toJSONString(hk)));
        return JSON.parseObject(value, vClass);
    }


    /**
     * 插入一个对象
     * 序列化成json字符串插入
     */
    public void setValue(String key, Object value) {
        setValue(key, JSON.toJSONString(value));
    }

    /**
     * 插入一个对象
     *
     * @param timeout 过期时间默认毫秒
     */
    public void setValue(String key, Object value, Long timeout) {
        this.setValue(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 插入一个对象
     *
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     */
    public void setValue(String key, Object value, Long timeout, TimeUnit timeUnit) {
        this.setValue(key, JSON.toJSONString(value), timeout, timeUnit);
    }

    /**
     * 插入一个字符串
     */
    public void setValue(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 插入的时候设置过期时间
     *
     * @param timeout 超时时间默认为毫秒
     */
    public void setValue(String key, String value, long timeout) {
        this.setValue(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 插入的时候设置过期时间
     */
    public void setValue(String key, String value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, timeUnit);
    }

    /**
     * 通过key获取对象
     */
    public <T> T getValue(String key, Class<T> cls) {
        String json = getValue(key);
        try {
            return JSON.parseObject(json, cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过key获取字符串
     */
    public String getValue(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 删除key
     */
    public void deleteValue(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除多个key
     */
    public void deleteValue(List<String> key) {
        stringRedisTemplate.delete(key);
    }
}
