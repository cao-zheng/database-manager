package com.github.dragonhht.database.manager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * JSON工具类.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public enum  JsonUtils {
    /** 实例. */
    INSTANCE;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 将JSON转成对象.
     * @param json json
     * @param tClass 目标类
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T readValue(String json, Class<T> tClass) throws IOException {
        T obj = mapper.readValue(json, tClass);
        return obj;
    }

    /**
     * 将对象转成json字符串.
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
