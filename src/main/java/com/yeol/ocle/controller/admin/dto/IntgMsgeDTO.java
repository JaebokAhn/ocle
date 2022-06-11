package com.yeol.ocle.controller.admin.dto;

import lombok.Data;

@Data
public class IntgMsgeDTO {
    private String intgMsgeId;  //통합메시지ID

    private String bswrDvsnCode;    //업무구분코드

    private String msgeDvsnCode;    //메시지구분코드

    private String msgeCntn;    //메시지내용
}
