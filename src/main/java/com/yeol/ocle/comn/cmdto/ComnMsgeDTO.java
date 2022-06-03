package com.yeol.ocle.comn.cmdto;

import lombok.Data;

import java.util.List;

@Data
public class ComnMsgeDTO {
    private String prcsRsltCode;    //처리결과코드
    private String msgeCode;    //메세지코드
    private String msgeCntn;    //메세지내용

    private List<String> fieldList; //BindingResult 결과 처리용 필드 목록
    private List<String> msgeList;  //BindingResult 결과 처리용 메시지 목록
}
