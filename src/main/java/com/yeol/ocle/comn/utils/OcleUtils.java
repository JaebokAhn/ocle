package com.yeol.ocle.comn.utils;

import org.thymeleaf.util.StringUtils;

/**
 * 오클 Utils
 */
public final class OcleUtils {
    /**
     * value가 null이거나 빈값인 경우 defaultVal을 return 한다.
     * @param value
     * @param defaultVal
     * @return
     */
    public static String nvlToString(String value, String defaultVal) {
        if(StringUtils.isEmpty(value)) {
            return defaultVal;
        }

        return value;
    }
}
