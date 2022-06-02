package com.yeol.ocle.comn.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public static <T> List<T> copyDtoList(List<?> sourceList, Class<T> targetType) {
        if (sourceList == null) {
            log.info("from source instance is null");
            return null;
        } else {
            List<T> targetList = new ArrayList();
            Iterator var3 = sourceList.iterator();

            while(var3.hasNext()) {
                Object element = var3.next();
                targetList.add(copyDto(element, targetType));
            }

            return targetList;
        }
    }
}
