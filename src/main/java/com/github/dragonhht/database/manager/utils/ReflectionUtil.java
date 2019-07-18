package com.github.dragonhht.database.manager.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 反射工具类.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Slf4j
public final class ReflectionUtil {

    /**
     * 创建实例.
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("类实例化失败", e);
        }
        return instance;
    }

    /**
     * 调用方法.
     * @param obj 对象
     * @param method 方法
     * @param args 参数
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("方法调用失败", e);
        }
        return result;
    }

    /**
     * 设置成员属性的值.
     * @param obj 对象
     * @param field 属性
     * @param value 值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            log.error("设置属性值失败", e);
        }
    }

    /**
     * 设置成员属性的值
     * @param target
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object target, String fieldName, Object value) {
        Field field = getField(target, fieldName);
        setField(target, field, value);
    }

    /**
     * 获取指定目标对象中的Field
     * @param target
     * @param fieldName
     * @return
     */
    public static Field getField(Object target, String fieldName) {

        for (Class<?> clazz = target.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {

            }
        }
        throw new RuntimeException("在类 " + target.getClass().getName() + " 中未找到 " + fieldName + " 属性");
    }

    /**
     * 获取Field值
     * @param target
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(target, fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    /**
     * 获取方法的参数信息.
     * @param method 方法
     * @return 参数信息
     */
    public static Parameter[] getMethodParameters(Method method) {
        Parameter parameter = method.getParameters()[0];
        return method.getParameters();
    }

}
