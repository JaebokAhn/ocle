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

    public static boolean isNumber(String number) {
        boolean flag = true;

        if(StringUtils.isEmpty(number)) {
            return false;
        }

        int size = number.length();
        int st_no = 0;

        // 45(-)음수여부 확인, 음수이면 시작위치를 1부터 시작
        if (number.charAt(0) == 45) {
            st_no = 1;
        }

        // 48(0)~57(9)가 아니면 false
        for (int i = st_no; i < size; ++i) {
            if (!(48 <= ((int) number.charAt(i)) && 57 >= ((int) number.charAt(i)))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
