package com.leno.base.util;

/**
 * <p>字符串工具类</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
public class StringUtil {

    public static String valueOf(Object object) {
        return object == null ? null : object.toString();
    }
}
