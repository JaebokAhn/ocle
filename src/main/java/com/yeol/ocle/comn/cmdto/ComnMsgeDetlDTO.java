package com.yeol.ocle.comn.cmdto;

import lombok.Data;

@Data
public class ComnMsgeDetlDTO {
    private String fieldName;   //필드명
    private String msgeCode;    //메시지코드
    private String msgeCntn;    //메시지내용
}
