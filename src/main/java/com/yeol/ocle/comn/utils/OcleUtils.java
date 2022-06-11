package com.yeol.ocle.comn.utils;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.cmdto.ComnMsgeDetlDTO;
import com.yeol.ocle.comn.cmdto.PageNavInfoDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Controller의 input parameter validation 후 결과(BindingResult)를
     * 공통메시지DTO (ComnMsgeDTO) type으로 변환해준다.
     *
     * @param bindingResult
     * @return
     */
    public static ComnMsgeDTO convertBindingResultToComnMsge (BindingResult bindingResult) {
        ComnMsgeDTO comnMsge = null;

        if(!bindingResult.hasErrors()) {
            //처리결과코드 : O (정상)
//            comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_O);
//            comnMsge.setMsgeCntn("정상적으로 처리되었습니다.");
            return comnMsge;
        }

        FieldError firstError = (FieldError) bindingResult.getAllErrors().get(0);

        //처리결과코드 : E (오류)
        comnMsge = new ComnMsgeDTO();
        comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_E);
        comnMsge.setMsgeCode(firstError.getCode());  //메시지코드
        comnMsge.setMsgeCntn(firstError.getDefaultMessage());    //메시지내용

        //BindingResult 결과 처리용 메시지상세목록
        List<ComnMsgeDetlDTO> msgeDeilList = new ArrayList<ComnMsgeDetlDTO>();

        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;

            ComnMsgeDetlDTO comnMsgeDetl = new ComnMsgeDetlDTO();

            comnMsgeDetl.setFieldName(fieldError.getField());   //필드명
            comnMsgeDetl.setMsgeCode(fieldError.getCode()); //메시지코드
            comnMsgeDetl.setMsgeCntn(fieldError.getDefaultMessage());   //메시지내용

            msgeDeilList.add(comnMsgeDetl);
        });

        comnMsge.setMsgeDetlList(msgeDeilList); //메시지상세목록
        return comnMsge;
    }

    /**
     * 정상처리결과메시지
     * @return ComnMsgeDTO comnMsge
     */
    public static ComnMsgeDTO getNormPrcsRsltMsge() {
        ComnMsgeDTO comnMsge = new ComnMsgeDTO();
        comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_O);   //처리결과코드 : O (정상)
        comnMsge.setMsgeCntn("정상적으로 처리되었습니다.");
        return comnMsge;
    }

    /**
     * 정상조회결과메시지
     * @return ComnMsgeDTO comnMsge
     */
    public static ComnMsgeDTO getNormInqyRsltMsge() {
        ComnMsgeDTO comnMsge = new ComnMsgeDTO();
        comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_O);   //처리결과코드 : O (정상)
        comnMsge.setMsgeCntn("조회가 완료되었습니다.");
        return comnMsge;
    }
}
