package com.yeol.ocle.comn.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public abstract class BeanCopyUtils extends BeanUtils {
    public static <T> T copyDto(Object from, Class<T> targetType) {
        if (from == null) {
            log.info("from source instance is null");
            return null;
        } else {
            Object target = null;

            try {
                target = targetType.newInstance();
                copyProperties(from, target);
                return (T) target;
            } catch (IllegalAccessException | InstantiationException var4) {
                log.warn("copyList Exception ={}", var4);
                return (T) target;
            }
        }
    }
}
