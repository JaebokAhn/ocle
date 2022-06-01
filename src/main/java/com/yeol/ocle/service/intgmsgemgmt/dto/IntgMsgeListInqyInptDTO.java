package com.yeol.ocle.service.intgmsgemgmt.dto;

import lombok.Data;

@Data
public class IntgMsgeListInqyInptDTO {
    //통합메시지ID
    private String intgMsgeId;

    //업무구분코드
    private String bswrDvsnCode;

    //메시지구분코드
    private String msgeDvsnCode;

    //메시지내용
    private String msgeCntn;
}
