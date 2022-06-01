package com.yeol.ocle.comn.utils;

import com.yeol.ocle.comn.cmdto.PageNavInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 페이징처리를 위해 시작페이지, 종료페이지를 구한다.
     * @param object
     * @return
     */
    public static PageNavInfoDTO getPageNavInfo(Page<Object> object) {
        Pageable pageable = object.getPageable();

        int startPage = Math.max(1, pageable.getPageNumber() - 4);
        int endPage = Math.min(object.getTotalPages(), pageable.getPageNumber() + 4);
        int currPage = pageable.getPageNumber();

        //page번호는 0부터 시작함.
        PageNavInfoDTO pageNavInfo = new PageNavInfoDTO();
        pageNavInfo.setStartPage(startPage);    //시작페이지번호
        pageNavInfo.setEndPage(endPage);    //종료페이지번호
        pageNavInfo.setCurrPage(currPage);  //현재페이지번호
        pageNavInfo.setTotalPage(object.getTotalPages());   //총페이지수
        return pageNavInfo;
    }
}
