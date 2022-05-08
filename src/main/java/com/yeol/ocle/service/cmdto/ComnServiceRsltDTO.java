package com.yeol.ocle.service.cmdto;

import lombok.Data;

@Data
public class ComnServiceRsltDTO {
    private String prcsRsltCode;    //처리결과코드
    private String msgeCode;    //메세지코드
    private String msgeCntn;    //메세지내용
}
