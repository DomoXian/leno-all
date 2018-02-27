package com.leno.aop;

import com.leno.base.norm.Auth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * <p>TODO</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
@Aspect
@Component
public class LenoAspect {

    @Around("@annotation(com.leno.base.norm.Auth)")
    public Object authAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();

        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Auth auth = methodSignature.getMethod().getAnnotation(Auth.class);
        if (!auth.needAuth()) {
            return joinPoint.proceed();
        }
        // 获取入参
        Object[] params = joinPoint.getArgs();
        // 获取注解类
        if (params == null || params.length == 0) {
            return joinPoint.proceed();
        }
        for (Object item : params) {
            if (item == null) {
                continue;
            }
        }
        return joinPoint.proceed(params);
    }
}
