package com.raysonxin.rpc.server;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.raysonxin.rpc.exception.MethodMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: BaseMethodMatcher.java
 * @author: raysonxin
 * @date: 2020/2/12 8:36 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Slf4j
public abstract class BaseMethodMatcher implements MethodMatcher {

    private final ConcurrentHashMap<MethodMatchInput, MethodMatchOutput> cache = new ConcurrentHashMap<>();//Maps.newConcurrentMap();

    @Override
    public MethodMatchOutput selectOneBestMatchMethod(MethodMatchInput input) {
        return cache.computeIfAbsent(input, in -> {
            try {
                MethodMatchOutput output = new MethodMatchOutput();
                Class<?> interfaceClass = Class.forName(in.getInterfaceName());
                HostClassMethodInfo info = findHostClassMethodInfo(interfaceClass);
                List<Method> targetMethods = Lists.newArrayList();

                ReflectionUtils.doWithMethods(info.getHostUserClass(), targetMethods::add, method -> {
                    String methodName = method.getName();
                    Class<?> declaringClass = method.getDeclaringClass();
                    List<Class<?>> inputParameterTypes = Optional.ofNullable(in.getMethodArgumentSignatures())
                            .map(mas -> {
                                List<Class<?>> list = Lists.newArrayList();
                                mas.forEach(ma -> list.add(ClassUtils.resolveClassName(ma, null)));
                                return list;
                            }).orElse(Lists.newArrayList());
                    output.setParameterTypes(inputParameterTypes);
                    if (!inputParameterTypes.isEmpty()) {
                        List<Class<?>> paramterTypes = Lists.newArrayList(method.getParameterTypes());
                        return Objects.equals(methodName, in.getMethodName())
                                && Objects.equals(info.getHostUserClass(), declaringClass)
                                && Objects.equals(paramterTypes, inputParameterTypes);
                    }
                    if (in.getMethodArgumentArraySize() > 0) {
                        List<Class<?>> paramterTypes = Lists.newArrayList(method.getParameterTypes());
                        return Objects.equals(methodName, in.getMethodName())
                                && Objects.equals(info.getHostUserClass(), declaringClass)
                                && in.getMethodArgumentArraySize() == paramterTypes.size();
                    }
                    return Objects.equals(methodName, in.getMethodName())
                            && Objects.equals(info.getHostUserClass(), declaringClass);
                });

                if (targetMethods.size() != 1) {
                    throw new MethodMatchException(String.format("找到目标方法数量不等于1，interface:%s,method:%s",
                            in.getInterfaceName(), in.getMethodName()));
                }

                Method targetMethod = targetMethods.get(0);
                output.setTargetClass(info.getHostClass());
                output.setTargetMethod(targetMethod);
                output.setTargetUserClass(info.getHostUserClass());
                output.setTarget(info.getHostTarget());
                return output;
            } catch (Exception ex) {
                log.error("查找匹配度最高的方法失败,输入参数:{}", JSON.toJSONString(in), ex);
                if (ex instanceof MethodMatchException) {
                    throw (MethodMatchException) ex;
                } else {
                    throw new MethodMatchException(ex);
                }
            }
        });
        // return null;
    }

    abstract HostClassMethodInfo findHostClassMethodInfo(Class<?> interfaceClass);
}
