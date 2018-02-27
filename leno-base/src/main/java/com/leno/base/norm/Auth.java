package com.leno.base.norm;

import java.lang.annotation.*;

/**
 * <p>授权注解</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Auth {
    boolean needAuth() default true;// 是否需要验证
}
