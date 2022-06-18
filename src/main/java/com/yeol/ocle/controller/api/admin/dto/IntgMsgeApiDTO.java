package com.yeol.ocle.controller.api.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IntgMsgeApiDTO {
    private String jobDvsnCode; //작업구분코드

    private String intgMsgeId;  //통합메시지ID

    private String bswrDvsnCode;    //업무구분코드

    private String msgeDvsnCode;    //메시지구분코드

    private String msgeCntn;    //메시지내용
}
